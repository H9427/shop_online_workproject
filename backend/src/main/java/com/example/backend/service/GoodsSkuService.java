package com.example.backend.service;

import com.example.backend.entity.bean.GoodsSku;

import java.util.List;

public interface GoodsSkuService {
    public List<GoodsSku> listGoodsSku(Integer goodsId);

    public GoodsSku getGoodsById(Integer skuId);
}
