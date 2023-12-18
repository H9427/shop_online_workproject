package com.example.backend.service;

import com.example.backend.entity.bean.OrderItem;
import com.example.backend.entity.vo.request.OrderAddGoodRequset;
import com.example.backend.entity.vo.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    public List<OrderItemResponse> addOrderItem(Integer orderId, List<OrderAddGoodRequset> orderAddGoodRequset);

    public List<OrderItemResponse> getOrderItemByOrderId(Integer orderId);

    public boolean deleteOrderItemsByOrderId(Integer orderId);

    public void addOrderItemSoldNum(Integer orderId);
}
