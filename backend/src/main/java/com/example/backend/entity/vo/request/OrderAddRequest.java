package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderAddRequest {
    private BigDecimal totalAmount;
    private String orderRemark;
    private List<OrderAddGoodRequset> Goods;
}
