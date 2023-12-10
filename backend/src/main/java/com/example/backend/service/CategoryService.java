package com.example.backend.service;

import com.example.backend.entity.vo.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    public List<CategoryResponse> queryAllCategory();
}
