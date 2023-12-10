package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.GoodsSku;
import com.example.backend.mapper.GoodsSkuMapper;
import com.example.backend.service.GoodsSkuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsSkuServiceImpl extends ServiceImpl<GoodsSkuMapper, GoodsSku> implements GoodsSkuService {
    @Override
    public GoodsSku listGoodsSku(Integer goodsId) {
        return query().eq("goods_id", goodsId).one();
    }
}
