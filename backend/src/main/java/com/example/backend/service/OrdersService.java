package com.example.backend.service;

import com.example.backend.entity.vo.request.OrderAddRequest;
import com.example.backend.entity.vo.request.OrdersDeleteRequest;
import com.example.backend.entity.vo.response.OrdersResponse;

import java.util.List;

public interface OrdersService {
    public OrdersResponse addOrder(Integer userId,OrderAddRequest orderAddRequest);

    public List<OrdersResponse> listOrders(Integer userId);

    public boolean deleteOrder(OrdersDeleteRequest ordersDeleteRequest);
}
