package com.example.backend.entity.vo.request;

import lombok.Data;

@Data
public class OrdersEditStateRequest {
    private Integer orderId;
    private Integer state;
}
