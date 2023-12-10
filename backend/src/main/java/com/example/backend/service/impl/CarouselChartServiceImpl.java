package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.CarouselChart;
import com.example.backend.entity.bean.Category;
import com.example.backend.entity.vo.response.CarouselChartResponse;
import com.example.backend.mapper.CarouselChartMapper;
import com.example.backend.service.CarouselChartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarouselChartServiceImpl extends ServiceImpl<CarouselChartMapper, CarouselChart> implements CarouselChartService {
    @Override
    public List<CarouselChartResponse> getChartsByType(String chartType) {
        LambdaQueryWrapper<CarouselChart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CarouselChart::getChartType, chartType);
        wrapper.orderByDesc(CarouselChart::getChartOrder);
        List<CarouselChart> carouselCharts = baseMapper.selectList(wrapper);
        List<CarouselChartResponse> carouselChartResponses = new ArrayList<>();
        for (CarouselChart carouselChart:carouselCharts){
            CarouselChartResponse carouselChartResponse = new CarouselChartResponse();
            carouselChartResponse.setImgId(carouselChart.getImgId());
            carouselChartResponse.setImgUrl(carouselChart.getImgUrl());
            carouselChartResponse.setGoodsId(carouselChart.getGoodsId());
            carouselChartResponse.setCategoryId(carouselChart.getCategoryId());
            carouselChartResponse.setChartType(carouselChart.getChartType());
            carouselChartResponse.setChartOrder(carouselChart.getChartOrder());
            carouselChartResponse.setStatus(carouselChart.getStatus());
            carouselChartResponse.setCreateTime(carouselChart.getCreateTime());
            carouselChartResponses.add(carouselChartResponse);
        }
        return carouselChartResponses;
    }

    @Override
    public List<CarouselChartResponse> getChartsByRandom(Integer number) {
        LambdaQueryWrapper<CarouselChart> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(CarouselChart::getChartOrder);
        wrapper.last("limit" + number);
        List<CarouselChart> carouselCharts = baseMapper.selectList(wrapper);
        List<CarouselChartResponse> carouselChartResponses = new ArrayList<>();
        for (CarouselChart carouselChart:carouselCharts){
            CarouselChartResponse carouselChartResponse = new CarouselChartResponse();
            carouselChartResponse.setImgId(carouselChart.getImgId());
            carouselChartResponse.setImgUrl(carouselChart.getImgUrl());
            carouselChartResponse.setGoodsId(carouselChart.getGoodsId());
            carouselChartResponse.setCategoryId(carouselChart.getCategoryId());
            carouselChartResponse.setChartType(carouselChart.getChartType());
            carouselChartResponse.setChartOrder(carouselChart.getChartOrder());
            carouselChartResponse.setStatus(carouselChart.getStatus());
            carouselChartResponse.setCreateTime(carouselChart.getCreateTime());
            carouselChartResponses.add(carouselChartResponse);
        }
        return carouselChartResponses;
    }
}
