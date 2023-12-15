package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Category;
import com.example.backend.entity.bean.GoodsSku;
import com.example.backend.mapper.GoodsSkuMapper;
import com.example.backend.service.GoodsSkuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodsSkuServiceImpl extends ServiceImpl<GoodsSkuMapper, GoodsSku> implements GoodsSkuService {
    @Override
    public List<GoodsSku> listGoodsSku(Integer goodsId) {
        LambdaQueryWrapper<GoodsSku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsSku::getGoodsId, goodsId);
        wrapper.orderByDesc(GoodsSku::getSkuId);
        List<GoodsSku> goodsSkus = baseMapper.selectList(wrapper);
        return goodsSkus;
    }

    @Override
    public GoodsSku getGoodsById(Integer skuId) {
        GoodsSku goodsSku = baseMapper.selectById(skuId);
        return goodsSku;
    }


}
