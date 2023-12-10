package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.GoodsImg;
import com.example.backend.mapper.GoodsImgMapper;
import com.example.backend.service.GoodsImgService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsImgServiceImpl extends ServiceImpl<GoodsImgMapper, GoodsImg> implements GoodsImgService {
    @Override
    public GoodsImg listGoodsImg(Integer goodsId) {
        return query().eq("goods_id", goodsId).one();
    }
}
