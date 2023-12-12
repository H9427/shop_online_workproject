package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CategoryResponse {
    private Integer categoryId;
    private String categoryName;
    private Integer categoryLevel;//分类层级
    private Integer parentId;//父层级id
    private List<CategoryResponse> childen;
}
