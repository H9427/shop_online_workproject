package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class FavoreteResponse {
    private Integer id;
    private Integer goodsId;
    private Integer userId;
    private GoodsResponse goodsResponse;
    private Date createTime;
}
