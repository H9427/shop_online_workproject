package com.example.backend.service;

import com.example.backend.entity.vo.response.CategoryClassAResponse;

import java.util.List;

public interface CategoryService {
    public List<CategoryClassAResponse> queryAllCategory();
}
