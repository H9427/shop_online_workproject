package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Category;
import com.example.backend.entity.vo.response.CategoryResponse;
import com.example.backend.mapper.CategoryMapper;
import com.example.backend.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<CategoryResponse> queryAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getCategoryLevel, 0);
        wrapper.orderByDesc(Category::getCategoryId);
        List<Category> categories = baseMapper.selectList(wrapper);
        if(categories == null){
            return null;
        }
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories){
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(category.getCategoryId());
            categoryResponse.setCategoryName(category.getCategoryName());
            categoryResponse.setCategoryLevel(category.getCategoryLevel());
            categoryResponse.setParentId(category.getParentId());
            categoryResponse.setCategoryIcon(category.getCategoryIcon());
            categoryResponse.setCategorySlogan(category.getCategorySlogan());
            categoryResponse.setDeleteFlag(category.getDeleteFlag());
            categoryResponse.setCreateTime(category.getCreateTime());
            categoryResponse.setChilden(queryCategory(category.getCategoryId()));
            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }

    //查子集
    private List<CategoryResponse> queryCategory(Integer categoryId){
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, categoryId);
        wrapper.orderByDesc(Category::getCategoryId);
        List<Category> categories = baseMapper.selectList(wrapper);
        if(categories == null){
            return null;
        }
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories){
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(category.getCategoryId());
            categoryResponse.setCategoryName(category.getCategoryName());
            categoryResponse.setCategoryLevel(category.getCategoryLevel());
            categoryResponse.setParentId(category.getParentId());
            categoryResponse.setCategoryIcon(category.getCategoryIcon());
            categoryResponse.setCategorySlogan(category.getCategorySlogan());
            categoryResponse.setDeleteFlag(category.getDeleteFlag());
            categoryResponse.setCreateTime(category.getCreateTime());
            categoryResponse.setChilden(queryCategory(category.getCategoryId()));
            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }
}
