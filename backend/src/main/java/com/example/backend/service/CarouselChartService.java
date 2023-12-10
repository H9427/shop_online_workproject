package com.example.backend.service;

import com.example.backend.entity.vo.response.CarouselChartResponse;

import java.util.List;

public interface CarouselChartService {
    //获取轮播图通过轮播图类型
    public List<CarouselChartResponse> getChartsByType(String chartType);

    //随机获取轮播图
    public List<CarouselChartResponse> getChartsByRandom(Integer number);
}
