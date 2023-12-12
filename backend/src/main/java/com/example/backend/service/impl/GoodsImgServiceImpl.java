package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.GoodsImg;
import com.example.backend.entity.bean.GoodsSku;
import com.example.backend.mapper.GoodsImgMapper;
import com.example.backend.service.GoodsImgService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodsImgServiceImpl extends ServiceImpl<GoodsImgMapper, GoodsImg> implements GoodsImgService {
    @Override
    public List<GoodsImg> listGoodsImg(Integer goodsId) {
        LambdaQueryWrapper<GoodsImg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsImg::getGoodsId, goodsId);
        wrapper.orderByDesc(GoodsImg::getId);
        List<GoodsImg> goodsImg = baseMapper.selectList(wrapper);
        return goodsImg;
    }
}
