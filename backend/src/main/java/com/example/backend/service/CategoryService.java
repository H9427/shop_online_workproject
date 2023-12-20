package com.example.backend.service;

import com.example.backend.entity.vo.response.CategoryClassAResponse;
import com.example.backend.entity.vo.response.GoodsResponse;

import java.util.List;

public interface CategoryService {
    public List<CategoryClassAResponse> queryAllCategory();

}
