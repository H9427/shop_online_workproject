package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.bean.GoodsImg;
import com.example.backend.entity.bean.GoodsParams;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsParamsMapper extends BaseMapper<GoodsParams> {
}
