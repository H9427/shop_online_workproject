package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Category;
import com.example.backend.entity.bean.Goods;
import com.example.backend.entity.vo.response.GoodsResponse;
import com.example.backend.mapper.GoodsMapper;
import com.example.backend.service.GoodsImgService;
import com.example.backend.service.GoodsService;
import com.example.backend.service.GoodsSkuService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    GoodsImgService goodsImgService;

    @Resource
    GoodsSkuService goodsSkuService;

    @Override
    public GoodsResponse goodsDetails(Integer goodsId) {
        Goods goods = query().eq("goods_id", goodsId).one();
        GoodsResponse goodsResponse = new GoodsResponse();
        goodsResponse.setGoodsId(goods.getGoodsId());
        goodsResponse.setGoodsName(goods.getGoodsName());
        goodsResponse.setCategoryId(goods.getCategoryId());
        goodsResponse.setRootCategoryId(goods.getRootCategoryId());
        goodsResponse.setSoldNum(goods.getSoldNum());
        goodsResponse.setGoodsTrait(goods.getGoodsTrait());
        goodsResponse.setInstructions(goods.getInstructions());
        goodsResponse.setMaterial(goods.getMaterial());
        goodsResponse.setGoodsImg(goodsImgService.listGoodsImg(goodsId));
        goodsResponse.setGoodsSku(goodsSkuService.listGoodsSku(goodsId));
        return goodsResponse;
    }

    @Override
    public List<GoodsResponse> listGoodsByCategoryId(Integer categoryId) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getCategoryId, categoryId);
        wrapper.orderByDesc(Goods::getGoodsId);
        List<Goods> goodsList = baseMapper.selectList(wrapper);
        List<GoodsResponse> goodsResponses = new ArrayList<>();
        for (Goods goods:goodsList){
            GoodsResponse goodsResponse = new GoodsResponse();
            goodsResponse.setGoodsId(goods.getGoodsId());
            goodsResponse.setGoodsName(goods.getGoodsName());
            goodsResponse.setCategoryId(goods.getCategoryId());
            goodsResponse.setRootCategoryId(goods.getRootCategoryId());
            goodsResponse.setSoldNum(goods.getSoldNum());
            goodsResponse.setGoodsTrait(goods.getGoodsTrait());
            goodsResponse.setInstructions(goods.getInstructions());
            goodsResponse.setMaterial(goods.getMaterial());
            goodsResponse.setGoodsImg(goodsImgService.listGoodsImg(goods.getGoodsId()));
            goodsResponse.setGoodsSku(goodsSkuService.listGoodsSku(goods.getGoodsId()));
            goodsResponses.add(goodsResponse);
        }
        return goodsResponses;
    }
}
