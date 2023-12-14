package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Orders;
import com.example.backend.entity.vo.request.OrderAddRequest;
import com.example.backend.entity.vo.response.OrdersResponse;
import com.example.backend.mapper.OrdersMapper;
import com.example.backend.service.OrderItemService;
import com.example.backend.service.OrdersService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    OrdersMapper ordersMapper;

    @Resource
    OrderItemService orderItemService;

    @Override
    public OrdersResponse addOrder(Integer userId,OrderAddRequest orderAddRequest) {
        System.out.println(orderAddRequest);
        OrdersResponse ordersResponse = new OrdersResponse();
        try{
            Orders order = new Orders(null,
                    userId,
                    null,
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
            System.out.println(ordersResponse);
            ordersResponse.setItems(orderItemService.addOrderItem(order.getId(),orderAddRequest.getGoods()));
            System.out.println(ordersResponse.getItems());
        }catch (Exception e){
            return null;
        }
        return ordersResponse;
    }
}
