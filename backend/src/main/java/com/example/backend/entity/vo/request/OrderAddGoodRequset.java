package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderAddGoodRequset {
    private Integer goodsId;
    private Integer skuId;
    private BigDecimal goodsPrice;
    private Integer count;
}
