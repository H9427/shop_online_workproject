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
    private String categoryIcon;//图标logo
    private String categorySlogan;//口号
    private Integer deleteFlag;//逻辑删除，1：正常  0：无效
    private Date createTime;//创建时间
    private List<CategoryResponse> childen;
}
