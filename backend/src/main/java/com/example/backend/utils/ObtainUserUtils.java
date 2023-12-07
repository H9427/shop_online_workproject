package com.example.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;

//通过request获取数据
@Component
public class ObtainUserUtils {

    public static String getUserName(HttpServletRequest request) throws ServerException {
        if (request.getParameter("userName") == null) {
            throw new ServerException("用户名不存在");
        }
        String userName = request.getParameter("userName");
        if (userName == null) {
            throw new ServerException("用户名不存在");
        }
        return userName;
    }

    public static String getUserMobile(HttpServletRequest request) throws ServerException {
        if (request.getParameter("userMobile") == null) {
            throw new ServerException("用户手机号不存在");
        }
        String userMobile = request.getParameter("userMobile");
        if (userMobile == null) {
            throw new ServerException("用户手机号不存在");
        }
        return userMobile;
    }
}