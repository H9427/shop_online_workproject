package com.example.backend.service;

import com.example.backend.entity.bean.GoodsImg;

import java.util.List;

public interface GoodsImgService {
    public List<GoodsImg> listGoodsImg(Integer goodsId);
}
