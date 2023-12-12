package com.example.backend.service;

import com.example.backend.entity.vo.response.CarouselChartResponse;

import java.util.List;

public interface CarouselChartService {

    //随机获取轮播图
    public List<CarouselChartResponse> getChartsByRandom(Integer number);
}
