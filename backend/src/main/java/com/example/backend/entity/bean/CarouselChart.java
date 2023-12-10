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
    private Integer categoryId;//商品分类id
    private String chartType;//轮播图类型
    private Integer chartOrder;//轮播图展示顺序
    private Integer status;//是否展示：1表示正常显示 0表示不显示
    private Date createTime;//创建时间
}
