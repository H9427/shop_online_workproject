package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.request.FavoriteAddRequest;
import com.example.backend.entity.vo.request.FavoriteCancelRequest;
import com.example.backend.entity.vo.request.OrderAddRequest;
import com.example.backend.entity.vo.response.CategoryClassAResponse;
import com.example.backend.entity.vo.response.FavoreteResponse;
import com.example.backend.entity.vo.response.OrdersResponse;
import com.example.backend.service.FavoriteService;
import com.example.backend.utils.JWTUtils;
import com.example.backend.utils.ObtainGoodsUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Resource
    FavoriteService favoriteService;

    @Resource
    JWTUtils jwtUtils;

    @Resource
    ObtainGoodsUtils ObtainGoodsUtils;

    @PostMapping("/addFavorite")
    @ResponseBody
    public void AddFavorite(FavoriteAddRequest favoriteAddRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        boolean flag = favoriteService.addFavorite(userId, favoriteAddRequest);
        response.setContentType("application/json;charset=utf-8");
        if(flag){
            response.getWriter().write(RestBean.success("我喜欢添加成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("我喜欢添加失败").asJsonString());
        }
    }

    @GetMapping("/queryAllFavorete")
    @ResponseBody
    public void QueryAllFavorete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        List<FavoreteResponse> favoreteResponses = favoriteService.queryAllFavorete(userId);
        response.setContentType("application/json;charset=utf-8");
        if(favoreteResponses != null){
            response.getWriter().write(RestBean.success(favoreteResponses,"查找成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查找失败").asJsonString());
        }
    }

    @PostMapping("/cancelFavorite")
    @ResponseBody
    public void CancelFavorite(FavoriteCancelRequest favoriteCancelRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag = favoriteService.cancelFavorite(favoriteCancelRequest);
        response.setContentType("application/json;charset=utf-8");
        if(flag){
            response.getWriter().write(RestBean.success("收藏删除成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("收藏删除失败").asJsonString());
        }
    }

    @GetMapping("/queryFavorete")
    @ResponseBody
    public void QueryFavorete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer goodsId = ObtainGoodsUtils.getGoodsId(request);
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        boolean flag = favoriteService.queryFavorete(userId, goodsId);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.success(flag,"查找成功").asJsonString());

    }
}
