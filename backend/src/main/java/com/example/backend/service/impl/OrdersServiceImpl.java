package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Orders;
import com.example.backend.entity.bean.ShopingCart;
import com.example.backend.entity.vo.request.OrderAddRequest;
import com.example.backend.entity.vo.request.OrdersDeleteRequest;
import com.example.backend.entity.vo.request.ShopingCartDeleteRequest;
import com.example.backend.entity.vo.response.OrdersResponse;
import com.example.backend.mapper.OrdersMapper;
import com.example.backend.service.OrderItemService;
import com.example.backend.service.OrdersService;
import com.example.backend.service.UserAddressService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    OrdersMapper ordersMapper;

    @Resource
    OrderItemService orderItemService;

    @Resource
    UserAddressService userAddressService;

    @Override
    public OrdersResponse addOrder(Integer userId,OrderAddRequest orderAddRequest) {
        Integer defaultAddress = userAddressService.getDefaultAddress(userId);
        OrdersResponse ordersResponse = new OrdersResponse();
        try{
            Orders order = new Orders(null,
                    userId,
                    defaultAddress,
                    orderAddRequest.getTotalAmount(),
                    orderAddRequest.getOrderRemark(),
                    1,
                    (int)(Math.random() * 10000000 + 10000000),
                    new Date(),
                    null,
                    null,
                    null,
                    null);
            ordersMapper.addOrders(order);
            ordersResponse.setId(order.getId());
            ordersResponse.setUserId(order.getUserId());
            ordersResponse.setAddressId(order.getAddressId());
            ordersResponse.setTotalAmount(order.getTotalAmount());
            ordersResponse.setOrderRemark(order.getOrderRemark());
            ordersResponse.setStatus(order.getStatus());
            ordersResponse.setDeliveryFlowId(order.getDeliveryFlowId());
            ordersResponse.setCreateTime(order.getCreateTime());
            ordersResponse.setPayTime(order.getPayTime());
            ordersResponse.setDeliveryTime(order.getDeliveryTime());
            ordersResponse.setFlishTime(order.getFlishTime());
            ordersResponse.setCancelTime(order.getCancelTime());
            ordersResponse.setItems(orderItemService.addOrderItem(order.getId(),orderAddRequest.getGoods()));
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return ordersResponse;
    }

    @Override
    public List<OrdersResponse> listOrders(Integer userId) {
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getUserId, userId);
        wrapper.orderByDesc(Orders::getCreateTime);
        List<Orders> orders = baseMapper.selectList(wrapper);
        List<OrdersResponse> ordersResponses = new ArrayList<>();
        for (Orders order:orders){
            OrdersResponse ordersResponse = new OrdersResponse();
            ordersResponse.setId(order.getId());
            ordersResponse.setUserId(order.getUserId());
            ordersResponse.setAddressId(order.getAddressId());
            ordersResponse.setTotalAmount(order.getTotalAmount());
            ordersResponse.setOrderRemark(order.getOrderRemark());
            ordersResponse.setStatus(order.getStatus());
            ordersResponse.setDeliveryFlowId(order.getDeliveryFlowId());
            ordersResponse.setCreateTime(order.getCreateTime());
            ordersResponse.setPayTime(order.getPayTime());
            ordersResponse.setDeliveryTime(order.getDeliveryTime());
            ordersResponse.setFlishTime(order.getFlishTime());
            ordersResponse.setCancelTime(order.getCancelTime());
            ordersResponse.setItems(orderItemService.getOrderItemByOrderId(order.getId()));
            ordersResponses.add(ordersResponse);
        }
        return ordersResponses;
    }

    @Override
    public boolean deleteOrder(OrdersDeleteRequest ordersDeleteRequest) {
        int flag = baseMapper.deleteById(ordersDeleteRequest.getOrderId());
        boolean i = orderItemService.deleteOrderItemsByOrderId(ordersDeleteRequest.getOrderId());
        return flag == 1&&i;
    }
}
