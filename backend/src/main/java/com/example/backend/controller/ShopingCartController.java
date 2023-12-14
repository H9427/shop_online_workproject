package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.request.AddressAddRequest;
import com.example.backend.entity.vo.request.ShopingCartAddRequest;
import com.example.backend.service.ShopingCartService;
import com.example.backend.utils.JWTUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

        System.out.println(userId);

        boolean flag = shopingCartService.addShopingCart(userId, shopingCartAddRequest);
        response.setContentType("application/json;charset=utf-8");
        if(flag){
            response.getWriter().write(RestBean.success("商品添加成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("商品添加失败").asJsonString());
        }
    }

}
