package com.example.backend.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("category")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @TableId(type = IdType.AUTO)
    private Integer categoryId;
    private String categoryName;
    private Integer categoryLevel;//分类层级
    private Integer parentId;//父层级id
    private String categoryIcon;//图标logo
    private String categorySlogan;//口号
    private Integer deleteFlag;//逻辑删除，1：正常  0：无效
    private Date createTime;//创建时间
}
