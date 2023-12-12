package com.example.backend.service;

import com.example.backend.entity.bean.Goods;
import com.example.backend.entity.vo.response.GoodsResponse;

import java.util.List;

public interface GoodsService {

    public GoodsResponse goodsDetails(Integer goodsId);

    //根据分类查商品
    public List<GoodsResponse> listGoodsByCategoryId(Integer categoryId);
}
