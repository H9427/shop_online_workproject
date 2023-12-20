package com.example.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;

//通过request获取数据
@Component
public class ObtainGoodsUtils {

    public static Integer getGoodsId(HttpServletRequest request) throws ServerException {
        if (request.getParameter("goodsId") == null) {
            throw new ServerException("商品ID不存在");
        }
        Integer goodsId = Integer.valueOf(request.getParameter("goodsId"));
        if (goodsId == null) {
            throw new ServerException("商品ID不存在");
        }
        return goodsId;
    }


    public static String getGoodsName(HttpServletRequest request) throws ServerException {
        if (request.getParameter("goodsName") == null) {
            throw new ServerException("不存在");
        }
        String goodsName = request.getParameter("goodsName");
        if (goodsName == null) {
            throw new ServerException("不存在");
        }
        return goodsName;
    }

    public static Integer getNumber(HttpServletRequest request) throws ServerException {
        if (request.getParameter("number") == null) {
            throw new ServerException("不存在");
        }
        Integer number = Integer.valueOf(request.getParameter("number"));
        if (number == null) {
            throw new ServerException("不存在");
        }
        return number;
    }
}