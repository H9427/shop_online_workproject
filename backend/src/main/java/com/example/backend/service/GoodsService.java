package com.example.backend.service;

import com.example.backend.entity.bean.Goods;
import com.example.backend.entity.vo.response.GoodsResponse;

public interface GoodsService {
    public GoodsResponse listGoods(Integer goodsId);
}
