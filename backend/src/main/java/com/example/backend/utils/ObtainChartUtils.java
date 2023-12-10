package com.example.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;

//通过request获取数据
@Component
public class ObtainChartUtils {

    public static String getChartType(HttpServletRequest request) throws ServerException {
        if (request.getParameter("chartType") == null) {
            throw new ServerException("轮播图类型不存在");
        }
        String chartType = request.getParameter("chartType");
        if (chartType == null) {
            throw new ServerException("轮播图类型不存在");
        }
        return chartType;
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