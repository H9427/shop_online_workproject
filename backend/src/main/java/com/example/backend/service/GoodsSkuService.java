package com.example.backend.service;

import com.example.backend.entity.bean.GoodsParams;
import com.example.backend.entity.bean.GoodsSku;

public interface GoodsSkuService {
    public GoodsSku listGoodsSku(Integer goodsId);
}
