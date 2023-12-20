package com.example.backend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.request.UserInformationEditRequest;
import com.example.backend.entity.vo.response.UsersResponse;
import com.example.backend.service.PersonalCenterService;
import com.example.backend.utils.JWTUtils;
import com.example.backend.utils.ObtainUserUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/personal")
public class PersonalCenterController {

    @Resource
    PersonalCenterService personalCenterService;

    @Resource
    JWTUtils jwtutils;

    @Resource
    ObtainUserUtils obtainUserUtils;

    //查询用户信息
    @GetMapping("/queryInformation")
    @ResponseBody
    public void QueryInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //查询
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        UsersResponse usersVO = personalCenterService.queryInformation(userId);
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
    public void ModifyInformation(UserInformationEditRequest user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        UsersResponse usersVO = personalCenterService.modifyInformation(userId,user);
        response.setContentType("application/json;charset=utf-8");
        if(usersVO != null){
            response.getWriter().write(RestBean.success(usersVO,"个人信息修改成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("个人信息修改失败").asJsonString());
        }
    }

    @PostMapping("/avatar")
    @ResponseBody
    private void editUserAvatar(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取token，并通过token获取用户id
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtutils.resolveJwt(authorization);
        Integer userId = jwtutils.toId(jwt);

        String img = personalCenterService.editUserAvatar(userId, file);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.success(img,"头像修改成功").asJsonString());
    }
}
