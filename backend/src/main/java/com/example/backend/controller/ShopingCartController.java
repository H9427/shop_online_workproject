package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.request.AddressAddRequest;
import com.example.backend.entity.vo.request.ShopingCartAddRequest;
import com.example.backend.entity.vo.request.ShopingCartDeleteRequest;
import com.example.backend.entity.vo.response.AllItemsAndPricesResponse;
import com.example.backend.entity.vo.response.ShopingCartResponse;
import com.example.backend.entity.vo.response.UserAddressResponse;
import com.example.backend.service.ShopingCartService;
import com.example.backend.utils.JWTUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shopingCart")
public class ShopingCartController {

    @Resource
    JWTUtils jwtUtils;

    @Resource
    ShopingCartService shopingCartService;

    @PostMapping("/addShopingCart")
    @ResponseBody
    public void AddShopingCart(ShopingCartAddRequest shopingCartAddRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        boolean flag = shopingCartService.addShopingCart(userId, shopingCartAddRequest);
        response.setContentType("application/json;charset=utf-8");
        if(flag){
            response.getWriter().write(RestBean.success("商品添加成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("商品添加失败").asJsonString());
        }
    }

    @GetMapping("/listshopingCart")
    @ResponseBody
    public void ListShopingCart(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        List<ShopingCartResponse> shopingCartResponses = shopingCartService.listShopingCart(userId);
        response.setContentType("application/json;charset=utf-8");
        if(shopingCartResponses != null){
            response.getWriter().write(RestBean.success(shopingCartResponses,"查询成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查询失败").asJsonString());
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public void DeleteShopingCart(@RequestBody ShopingCartDeleteRequest shopingCartDeleteRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag = shopingCartService.deleteShopingCart(shopingCartDeleteRequest);
        response.setContentType("application/json;charset=utf-8");
        if(flag){
            response.getWriter().write(RestBean.success("删除成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("删除失败").asJsonString());
        }
    }

    @GetMapping("/allItemsAndPrices")
    @ResponseBody
    public void AllItemsAndPrices(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        AllItemsAndPricesResponse allItemsAndPricesResponse = shopingCartService.allItemsAndPrices(userId);
        response.setContentType("application/json;charset=utf-8");
        if(allItemsAndPricesResponse != null){
            response.getWriter().write(RestBean.success(allItemsAndPricesResponse,"查询成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查询失败").asJsonString());
        }
    }

}
