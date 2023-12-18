package com.example.backend.entity.vo.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllItemsAndPricesResponse {
    private Integer itemsCount;
    private BigDecimal prices;
}
