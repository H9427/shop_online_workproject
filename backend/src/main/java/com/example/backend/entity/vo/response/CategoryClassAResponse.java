package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryClassAResponse {
    private Integer categoryId;
    private String categoryName;
    private List<CategoryClassBResponse> childen;
}
