package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.bean.ShopingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopingCartMapper extends BaseMapper<ShopingCart> {
}