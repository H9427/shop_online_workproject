package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.request.AddressAddRequest;
import com.example.backend.entity.vo.request.AddressCommonRequest;
import com.example.backend.entity.vo.request.AddressDeleteRequest;
import com.example.backend.entity.vo.request.AddressEditRequest;
import com.example.backend.entity.vo.response.UserAddressResponse;
import com.example.backend.service.UserAddressService;
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
@RequestMapping("/api/address")
public class UserAddressController {

    @Resource
    UserAddressService userAddressService;

    @Resource
    JWTUtils jwtutils;

    @PostMapping("/addAddress")
    @ResponseBody
    public void AddAddress(AddressAddRequest addAddressRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        UserAddress userAddress = userAddressService.addAddress(userId, addAddressRequest);
        response.setContentType("application/json;charset=utf-8");
        if(userAddress != null){
            response.getWriter().write(RestBean.success(userAddress,"地址添加成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("已有默认地址").asJsonString());
        }
    }

    @GetMapping("/listAddress")
    @ResponseBody
    public void ListAddress(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        List<UserAddressResponse> userAddressResponses = userAddressService.listAddress(userId);
        response.setContentType("application/json;charset=utf-8");
        if(userAddressResponses != null){
            response.getWriter().write(RestBean.success(userAddressResponses,"查询成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查询失败").asJsonString());
        }
    }

    @PostMapping("/editAddress")
    @ResponseBody
    public void EditAddress(AddressEditRequest addressEditRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        UserAddressResponse userAddressResponse = userAddressService.editAddress(userId, addressEditRequest);
        response.setContentType("application/json;charset=utf-8");
        if(userAddressResponse != null){
            response.getWriter().write(RestBean.success(userAddressResponse,"修改成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("修改失败").asJsonString());
        }
    }

    @PostMapping("/deleteAddress")
    @ResponseBody
    public void DeleteAddress(AddressDeleteRequest addressDeleteRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        boolean state = userAddressService.deleteAddress(userId, addressDeleteRequest);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success("删除成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("删除失败").asJsonString());
        }
    }

    @PostMapping("/commonAddress")
    @ResponseBody
    public void CommonAddress(AddressCommonRequest addressCommonRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        boolean state = userAddressService.commonAddress(userId, addressCommonRequest);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success("修改成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("修改失败").asJsonString());
        }
    }
}
