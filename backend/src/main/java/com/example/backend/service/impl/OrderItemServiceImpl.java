package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.GoodsSku;
import com.example.backend.entity.bean.OrderItem;
import com.example.backend.entity.vo.request.OrderAddGoodRequset;
import com.example.backend.entity.vo.request.OrderAddRequest;
import com.example.backend.mapper.GoodsSkuMapper;
import com.example.backend.mapper.OrderItemMapper;
import com.example.backend.service.GoodsSkuService;
import com.example.backend.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    @Override
    public List<OrderItem> addOrderItem(Integer orderId, List<OrderAddGoodRequset> orderAddGoodRequsets) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderAddGoodRequset orderAddRequest:orderAddGoodRequsets){
            OrderItem orderItem = new OrderItem(null,
                    orderId,
                    orderAddRequest.getGoodsId(),
                    orderAddRequest.getSkuId(),
                    orderAddRequest.getGoodsPrice(),
                    orderAddRequest.getCount(),
                    0);
            int insert = baseMapper.insert(orderItem);
            orderItems.add(orderItem);
            if(insert != 1){
                return null;
            }
        }
        return orderItems;
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItem::getOrderId, orderId);
        wrapper.orderByAsc(OrderItem::getId);
        List<OrderItem> orderItems = baseMapper.selectList(wrapper);
        return orderItems;
    }

    @Override
    public boolean deleteOrderItemsByOrderId(Integer orderId) {
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItem::getOrderId, orderId);
        int i = baseMapper.delete(wrapper);
        return i >= 1;
    }
}
