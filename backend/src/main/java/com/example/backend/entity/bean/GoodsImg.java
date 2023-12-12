package com.example.backend.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//商品图片
@Data
@TableName("goods_img")
@AllArgsConstructor
@NoArgsConstructor
public class GoodsImg {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer goodsId;//商品id
    private String imgUrl;//图片地址
}
