package com.example.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;

@Component
public class ObtainUserUtils {

    public static String getUserName(HttpServletRequest request) throws ServerException {
        if (request.getAttribute("userName") == null) {
            System.out.println("输出了这里");
            throw new ServerException("用户名不存在");
        }
        String userName = request.getAttribute("userName").toString();
        if (userName == null) {
            throw new ServerException("用户名不存在");
        }
        return userName;
    }

    public static String getUserMobile(HttpServletRequest request) throws ServerException {
        if (request.getAttribute("userMobile") == null) {
            throw new ServerException("用户手机号不存在");
        }
        String userMobile = request.getAttribute("userMobile").toString();
        if (userMobile == null) {
            throw new ServerException("用户手机号不存在");
        }
        return userMobile;
    }
}