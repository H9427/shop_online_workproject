package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.response.GoodsResponse;
import com.example.backend.entity.vo.response.UsersResponse;
import com.example.backend.service.GoodsService;
import com.example.backend.utils.ObtainChartUtils;
import com.example.backend.utils.ObtainGoodsUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/goods")
public class GoodsController {

    @Resource
    ObtainGoodsUtils obtainGoodsUtils;

    @Resource
    GoodsService goodsService;

    @GetMapping("/details")
    @ResponseBody
    public void GoodsDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer goodsId = obtainGoodsUtils.getGoodsId(request);

        GoodsResponse goodsResponse = goodsService.goodsDetails(goodsId);
        response.setContentType("application/json;charset=utf-8");
        if(goodsResponse != null){
            response.getWriter().write(RestBean.success(goodsResponse,"查询成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查询失败").asJsonString());
        }
    }

    @GetMapping("/fuzzy")
    @ResponseBody
    public void FuzzyQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsName = obtainGoodsUtils.getGoodsName(request);
        List<GoodsResponse> goodsResponses = goodsService.fuzzyQuery(goodsName);
        response.setContentType("application/json;charset=utf-8");
        if(goodsResponses != null){
            response.getWriter().write(RestBean.success(goodsResponses,"查询成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查询失败").asJsonString());
        }
    }

    @GetMapping("/getByNum")
    @ResponseBody
    public void GetGoodsByNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = obtainGoodsUtils.getNumber(request);
        List<GoodsResponse> goodsResponses = goodsService.getGoodsByNum(number);
        response.setContentType("application/json;charset=utf-8");
        if(goodsResponses != null){
            response.getWriter().write(RestBean.success(goodsResponses,"查询成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查询失败").asJsonString());
        }
    }
}
