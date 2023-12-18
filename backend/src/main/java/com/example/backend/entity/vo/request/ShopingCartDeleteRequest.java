package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
//@AllArgsConstructor
public class ShopingCartDeleteRequest {
    private List<Integer> cartIds;
}
