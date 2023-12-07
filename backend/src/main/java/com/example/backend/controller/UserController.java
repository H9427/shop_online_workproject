package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.query.UserIdentityQuery;
import com.example.backend.entity.vo.query.UserInformationModifyQuery;
import com.example.backend.entity.vo.query.UserRegisterQuery;
import com.example.backend.entity.vo.query.UserResetPwdQuery;
import com.example.backend.entity.vo.response.UsersVO;
import com.example.backend.service.UsersService;
import com.example.backend.utils.JWTUtils;
import com.example.backend.utils.ObtainUserUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void Register(UserRegisterQuery query, HttpServletResponse response) throws IOException {
        boolean state = usersService.RegisterUser(query);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success().asJsonString());
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
        boolean state = usersService.IdentityAuthentication(userName,userMobile);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success().asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("填写错误").asJsonString());
        }
    }

    //重置密码
    @PostMapping("/resetPwd")
    @ResponseBody
    public void ResetPassword(UserResetPwdQuery query, HttpServletResponse response) throws IOException {
        boolean state = usersService.ResetPassword(query);
        response.setContentType("application/json;charset=utf-8");
        if(state){
            response.getWriter().write(RestBean.success().asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("未查询到用户信息").asJsonString());
        }
    }

    //查询个人信息
    @GetMapping("/queryInformation")
    @ResponseBody
    public void QueryInformation(String userName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //查询
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        UsersVO usersVO = usersService.QueryInformation(userId);
        response.setContentType("application/json;charset=utf-8");
        if(usersVO != null){
            response.getWriter().write(RestBean.success(usersVO).asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("未查询到用户信息").asJsonString());
        }
    }

    //修改个人信息
    @PostMapping("/modifyInformation")
    @ResponseBody
    public void ModifyInformation(UserInformationModifyQuery user, HttpServletRequest request,HttpServletResponse response) throws IOException {
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);
        UsersVO usersVO = usersService.ModifyInformation(userId,user);
        response.setContentType("application/json;charset=utf-8");
        if(usersVO != null){
            response.getWriter().write(RestBean.success(usersVO).asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("信息修改失败").asJsonString());
        }
    }
}
