package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsCommentsResponse {
    private Integer id;
    private Integer goodsId;
    private Integer userId;
    private Integer isAnonymous;
    private Integer commType;
    private String commContent;
    private String commImg;
    private Date commTime;
}
