package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.GoodsImg;
import com.example.backend.entity.bean.GoodsParams;
import com.example.backend.mapper.GoodsImgMapper;
import com.example.backend.mapper.GoodsParamsMapper;
import com.example.backend.service.GoodsImgService;
import com.example.backend.service.GoodsParamsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsParamsServiceImpl extends ServiceImpl<GoodsParamsMapper, GoodsParams> implements GoodsParamsService {

    @Override
    public GoodsParams listGoodsParams(Integer goodsId) {
        return query().eq("goods_id", goodsId).one();
    }
}
