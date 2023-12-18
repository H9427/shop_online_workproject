package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.GoodsSku;
import com.example.backend.entity.bean.OrderItem;
import com.example.backend.entity.vo.request.OrderAddGoodRequset;
import com.example.backend.entity.vo.request.OrderAddRequest;
import com.example.backend.entity.vo.response.OrderItemResponse;
import com.example.backend.mapper.GoodsSkuMapper;
import com.example.backend.mapper.OrderItemMapper;
import com.example.backend.service.GoodsService;
import com.example.backend.service.GoodsSkuService;
import com.example.backend.service.OrderItemService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Resource
    GoodsService goodsService;

    @Resource
    GoodsSkuService goodsSkuService;

    @Override
    public List<OrderItemResponse> addOrderItem(Integer orderId, List<OrderAddGoodRequset> orderAddGoodRequsets) {
        List<OrderItemResponse> orderItems = new ArrayList<>();
        try{
            for (OrderAddGoodRequset orderAddRequest:orderAddGoodRequsets){
                OrderItem orderItem = new OrderItem(null,
                        orderId,
                        orderAddRequest.getGoodsId(),
                        orderAddRequest.getSkuId(),
                        orderAddRequest.getGoodsPrice(),
                        orderAddRequest.getCount(),
                        0);
                baseMapper.insert(orderItem);
                OrderItemResponse orderItemResponse = new OrderItemResponse();
                orderItemResponse.setId(orderItem.getId());
                orderItemResponse.setOrderId(orderItem.getOrderId());
                orderItemResponse.setGoodsId(orderItem.getGoodsId());
                orderItemResponse.setSkuId(orderItem.getSkuId());
                orderItemResponse.setGoodsPrice(orderItem.getGoodsPrice());
                orderItemResponse.setCount(orderItem.getCount());
                orderItemResponse.setIsComment(orderItem.getIsComment());
                orderItemResponse.setGoods(goodsService.goodsDetails(orderItem.getGoodsId()));
                orderItemResponse.setSku(goodsSkuService.getGoodsById(orderItem.getSkuId()));
                orderItems.add(orderItemResponse);
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return orderItems;
    }

    @Override
    public List<OrderItemResponse> getOrderItemByOrderId(Integer orderId) {
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItem::getOrderId, orderId);
        wrapper.orderByAsc(OrderItem::getId);
        List<OrderItem> orderItems = baseMapper.selectList(wrapper);
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (OrderItem orderItem:orderItems){
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setId(orderItem.getId());
            orderItemResponse.setOrderId(orderItem.getOrderId());
            orderItemResponse.setGoodsId(orderItem.getGoodsId());
            orderItemResponse.setSkuId(orderItem.getSkuId());
            orderItemResponse.setGoodsPrice(orderItem.getGoodsPrice());
            orderItemResponse.setCount(orderItem.getCount());
            orderItemResponse.setIsComment(orderItem.getIsComment());
            orderItemResponse.setGoods(goodsService.goodsDetails(orderItem.getGoodsId()));
            orderItemResponse.setSku(goodsSkuService.getGoodsById(orderItem.getSkuId()));
            orderItemResponses.add(orderItemResponse);
        }
        return orderItemResponses;
    }

    @Override
    public boolean deleteOrderItemsByOrderId(Integer orderId) {
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItem::getOrderId, orderId);
        int i = baseMapper.delete(wrapper);
        return i >= 1;
    }

    @Override
    public void addOrderItemSoldNum(Integer orderId) {
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItem::getOrderId, orderId);
        List<OrderItem> orderItems = baseMapper.selectList(wrapper);
        for (OrderItem orderItem:orderItems){
            goodsService.addSoldNum(orderItem.getGoodsId(),orderItem.getCount());
        }
    }
}
