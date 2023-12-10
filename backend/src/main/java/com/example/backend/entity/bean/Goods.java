package com.example.backend.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//商品
@Data
@TableName("goods")
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer goodsId;
    private String goodsName;
    private Integer categoryId;
    private Integer rootCategoryId;
    private Integer soldNum;
    private Integer status;
    private String context;
    private Date createTime;
}
