package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Goods;
import com.example.backend.entity.vo.response.GoodsResponse;
import com.example.backend.mapper.GoodsMapper;
import com.example.backend.service.GoodsImgService;
import com.example.backend.service.GoodsParamsService;
import com.example.backend.service.GoodsService;
import com.example.backend.service.GoodsSkuService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    GoodsImgService goodsImgService;

    @Resource
    GoodsParamsService goodsParamsService;

    @Resource
    GoodsSkuService goodsSkuService;

    @Override
    public GoodsResponse listGoods(Integer goodsId) {
        Goods goods = query().eq("goods_id", goodsId).one();
        GoodsResponse goodsResponse = new GoodsResponse();
        goodsResponse.setGoodsId(goods.getGoodsId());
        goodsResponse.setGoodsName(goods.getGoodsName());
        goodsResponse.setCategoryId(goods.getCategoryId());
        goodsResponse.setRootCategoryId(goods.getRootCategoryId());
        goodsResponse.setSoldNum(goods.getSoldNum());
        goodsResponse.setStatus(goods.getStatus());
        goodsResponse.setContext(goods.getContext());
        goodsResponse.setCreateTime(goods.getCreateTime());
        goodsResponse.setGoodsImg(goodsImgService.listGoodsImg(goodsId));
        goodsResponse.setGoodsParams(goodsParamsService.listGoodsParams(goodsId));
        goodsResponse.setGoodsSku(goodsSkuService.listGoodsSku(goodsId));
        return goodsResponse;
    }
}
