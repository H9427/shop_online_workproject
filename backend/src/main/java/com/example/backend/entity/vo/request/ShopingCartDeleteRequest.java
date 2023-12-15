package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopingCartDeleteRequest {
    private Integer cartId;
}
