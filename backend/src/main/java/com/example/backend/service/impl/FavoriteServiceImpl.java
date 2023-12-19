package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.CarouselChart;
import com.example.backend.entity.bean.Favorite;
import com.example.backend.mapper.CarouselChartMapper;
import com.example.backend.mapper.FavoriteMapper;
import com.example.backend.service.CarouselChartService;
import com.example.backend.service.FavoriteService;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
}
