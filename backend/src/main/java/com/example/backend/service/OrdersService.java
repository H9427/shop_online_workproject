package com.example.backend.service;

import com.example.backend.entity.vo.request.OrderAddRequest;
import com.example.backend.entity.vo.response.OrdersResponse;

public interface OrdersService {
    public OrdersResponse addOrder(Integer userId,OrderAddRequest orderAddRequest);
}
