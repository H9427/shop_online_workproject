package com.example.backend.controller;

import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.response.CarouselChartResponse;
import com.example.backend.service.CarouselChartService;
import com.example.backend.utils.ObtainChartUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chart")
public class CarouselChartController {
    @Resource
    CarouselChartService carouselChartService;

    @Resource
    ObtainChartUtils obtainChartUtils;


    //随机获取轮播图：传递需要多少张
    @GetMapping("/getByRandom")
    @ResponseBody
    public void GetChartsByRandom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = obtainChartUtils.getNumber(request);
        List<CarouselChartResponse> charts = carouselChartService.getChartsByRandom(number);
        response.setContentType("application/json;charset=utf-8");
        if(charts != null){
            response.getWriter().write(RestBean.success(charts,"查找成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查找失败").asJsonString());
        }
    }
}
