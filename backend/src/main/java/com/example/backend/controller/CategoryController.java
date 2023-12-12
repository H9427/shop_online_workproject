package com.example.backend.controller;

import com.example.backend.entity.RestBean;
import com.example.backend.entity.vo.response.CategoryClassAResponse;
import com.example.backend.service.CategoryService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    //查询所有分类信息
    @GetMapping("/queryAllCategory")
    @ResponseBody
    public void QueryAllCategory(HttpServletResponse response) throws IOException {

        List<CategoryClassAResponse> categoryResponses = categoryService.queryAllCategory();
        response.setContentType("application/json;charset=utf-8");
        if(categoryResponses != null){
            response.getWriter().write(RestBean.success(categoryResponses,"查找成功").asJsonString());
        }else {
            response.getWriter().write(RestBean.unauthorized("查找失败").asJsonString());
        }
    }
}
