package com.example.backend.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("carousel_chart")
@AllArgsConstructor
@NoArgsConstructor
public class CarouselChart {
    @TableId(type = IdType.AUTO)
    private Integer imgId;
    private String imgUrl;
    private Integer goodsId;//商品id
    private Integer chartOrder;//轮播图展示顺序
}
