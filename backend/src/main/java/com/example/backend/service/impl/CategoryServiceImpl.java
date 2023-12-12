package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Category;
import com.example.backend.entity.vo.response.CategoryClassAResponse;
import com.example.backend.entity.vo.response.CategoryClassBResponse;
import com.example.backend.mapper.CategoryMapper;
import com.example.backend.service.CategoryService;
import com.example.backend.service.GoodsService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    GoodsService goodsService;

    @Override
    public List<CategoryClassAResponse> queryAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getCategoryLevel, 0);
        wrapper.orderByDesc(Category::getCategoryId);
        List<Category> categories = baseMapper.selectList(wrapper);
        if(categories == null){
            return null;
        }
        List<CategoryClassAResponse> categoryClassAResponses = new ArrayList<>();
        for (Category category : categories){
            CategoryClassAResponse categoryResponse = new CategoryClassAResponse();
            categoryResponse.setCategoryId(category.getCategoryId());
            categoryResponse.setCategoryName(category.getCategoryName());
            categoryResponse.setChilden(queryCategory(category.getCategoryId()));
            categoryClassAResponses.add(categoryResponse);
        }
        return categoryClassAResponses;
    }

    //查子集
    private List<CategoryClassBResponse> queryCategory(Integer categoryId){
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, categoryId);
        wrapper.orderByDesc(Category::getCategoryId);
        List<Category> categories = baseMapper.selectList(wrapper);
        if(categories == null){
            return null;
        }
        List<CategoryClassBResponse> categoryClassBResponses = new ArrayList<>();
        for (Category category : categories){
            CategoryClassBResponse categoryResponse = new CategoryClassBResponse();
            categoryResponse.setCategoryId(category.getCategoryId());
            categoryResponse.setCategoryName(category.getCategoryName());
            categoryResponse.setGoods(goodsService.listGoodsByCategoryId(category.getCategoryId()));
            categoryClassBResponses.add(categoryResponse);
        }
        return categoryClassBResponses;
    }
}
