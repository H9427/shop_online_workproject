package com.example.backend.controller;

import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.request.UserRegisterRequest;
import com.example.backend.entity.vo.request.UserResetPwdRequest;
import com.example.backend.service.UsersService;
import com.example.backend.utils.JWTUtils;
import com.example.backend.utils.ObtainUserUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UsersService usersService;

    @Resource
    JWTUtils jwtutils;

    @Resource
    ObtainUserUtils obtainUserUtils;

    //注册
    @PostMapping("/register")
    @ResponseBody
    public void Register(UserRegisterRequest query, HttpServletResponse response) throws IOException {
        boolean state = usersService.registerUser(query);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success("注册成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("注册失败").asJsonString());
        }
    }

    //信息修改时身份认证
    @GetMapping("/identity")
    @ResponseBody
    public void IdentityAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String userName = obtainUserUtils.getUserName(request);
        String userMobile = obtainUserUtils.getUserMobile(request);
        boolean state = usersService.identityAuthentication(userName,userMobile);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success("认证成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("填写错误").asJsonString());
        }
    }

    //重置密码
    @PostMapping("/resetPwd")
    @ResponseBody
    public void ResetPassword(UserResetPwdRequest query, HttpServletResponse response) throws IOException {
        boolean state = usersService.resetPassword(query);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success("密码修改成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("未查询到用户信息").asJsonString());
        }
    }


}
