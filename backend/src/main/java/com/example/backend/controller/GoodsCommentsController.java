package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.request.*;
import com.example.backend.entity.vo.response.*;
import com.example.backend.service.GoodsCommentsService;
import com.example.backend.service.OrdersService;
import com.example.backend.utils.JWTUtils;
import com.example.backend.utils.ObtainCommentsUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class GoodsCommentsController {

    @Resource
    ObtainCommentsUtils obtainCommentsUtils;

    @Resource
    JWTUtils jwtUtils;

    @Resource
    GoodsCommentsService goodsCommentsService;

    @PostMapping("/addComments")
    @ResponseBody
    public void AddComments(GoodsCommentsAddRequest goodsCommentsAddRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        GoodsCommentsResponse goodsCommentsResponse = goodsCommentsService.addComments(userId, goodsCommentsAddRequest);
        response.setContentType("application/json;charset=utf-8");
        if(goodsCommentsResponse != null){
            response.getWriter().write(RestBean.success(goodsCommentsResponse,"评论添加成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("评论添加失败").asJsonString());
        }
    }

    @PostMapping("/addCommentsNoPic")
    @ResponseBody
    public void AddCommentsNoPic(GoodsCommentsAddNoPicRequest goodsCommentsAddRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        GoodsCommentsResponse goodsCommentsResponse = goodsCommentsService.addCommentsNoPic(userId, goodsCommentsAddRequest);
        response.setContentType("application/json;charset=utf-8");
        if(goodsCommentsResponse != null){
            response.getWriter().write(RestBean.success(goodsCommentsResponse,"评论添加成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("评论添加失败").asJsonString());
        }
    }

    @GetMapping("/allComments")
    @ResponseBody
    public void AllComments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer goodsId = obtainCommentsUtils.getGoodsId(request);

        List<CommentsResonse> commentsResonses = goodsCommentsService.allComments(goodsId);
        response.setContentType("application/json;charset=utf-8");
        if(commentsResonses != null){
            response.getWriter().write(RestBean.success(commentsResonses,"查询成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查询失败").asJsonString());
        }
    }
}
