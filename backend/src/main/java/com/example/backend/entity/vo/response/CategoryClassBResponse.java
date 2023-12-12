package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryClassBResponse {
    private Integer categoryId;
    private String categoryName;
    private List<GoodsResponse> goods;
}
