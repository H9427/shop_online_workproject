package com.example.backend.service;

import com.example.backend.entity.bean.OrderItem;
import com.example.backend.entity.vo.request.OrderAddGoodRequset;

import java.util.List;

public interface OrderItemService {
    public List<OrderItem> addOrderItem(Integer orderId, List<OrderAddGoodRequset> orderAddGoodRequset);

    public List<OrderItem> getOrderItemByOrderId(Integer orderId);
}
