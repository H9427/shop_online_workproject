package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.request.OrderAddRequest;
import com.example.backend.entity.vo.request.OrdersDeleteRequest;
import com.example.backend.entity.vo.request.ShopingCartDeleteRequest;
import com.example.backend.entity.vo.request.UserInformationEditRequest;
import com.example.backend.entity.vo.response.OrdersResponse;
import com.example.backend.entity.vo.response.UsersResponse;
import com.example.backend.service.OrdersService;
import com.example.backend.utils.JWTUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrdersController {

    @Resource
    JWTUtils jwtUtils;

    @Resource
    OrdersService ordersService;

    @PostMapping("/addOrder")
    @ResponseBody
    public void AddOrder(@RequestBody OrderAddRequest orderAddRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        OrdersResponse ordersResponse = ordersService.addOrder(userId, orderAddRequest);
        response.setContentType("application/json;charset=utf-8");
        if(ordersResponse != null){
            response.getWriter().write(RestBean.success(ordersResponse,"订单添加成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("订单添加失败").asJsonString());
        }
    }

    @GetMapping("/listOrders")
    @ResponseBody
    public void ListOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //查询
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
        Integer userId = jwtUtils.toId(jwt);

        List<OrdersResponse> ordersResponses = ordersService.listOrders(userId);
        response.setContentType("application/json;charset=utf-8");
        if(ordersResponses != null){
            response.getWriter().write(RestBean.success(ordersResponses,"查询成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查询失败").asJsonString());
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public void DeleteOrder(OrdersDeleteRequest ordersDeleteRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean flag = ordersService.deleteOrder(ordersDeleteRequest);
        response.setContentType("application/json;charset=utf-8");
        if(flag){
            response.getWriter().write(RestBean.success("删除成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("删除失败").asJsonString());
        }
    }
}
