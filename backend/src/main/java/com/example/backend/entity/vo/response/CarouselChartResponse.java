package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class CarouselChartResponse {
    private Integer imgId;
    private String imgUrl;
    private Integer goodsId;//商品id
    private Integer chartOrder;//轮播图展示顺序
}
