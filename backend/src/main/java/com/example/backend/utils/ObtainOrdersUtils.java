package com.example.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;

//通过request获取数据
@Component
public class ObtainOrdersUtils {

    public static Integer getOrderId(HttpServletRequest request) throws ServerException {
        if (request.getParameter("orderId") == null) {
            throw new ServerException("Id不存在");
        }
        Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        if (orderId == null) {
            throw new ServerException("Id不存在");
        }
        return orderId;
    }


    public static Integer getNumber(HttpServletRequest request) throws ServerException {
        if (request.getParameter("number") == null) {
            throw new ServerException("数字不存在");
        }
        Integer number = Integer.valueOf(request.getParameter("number"));
        if (number == null) {
            throw new ServerException("数字不存在");
        }
        return number;
    }
}