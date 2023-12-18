package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.bean.Goods;
import com.example.backend.entity.bean.GoodsComments;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsCommentsMapper extends BaseMapper<GoodsComments> {
}
