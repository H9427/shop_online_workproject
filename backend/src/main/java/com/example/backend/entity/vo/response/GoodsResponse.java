package com.example.backend.entity.vo.response;

import com.example.backend.entity.bean.GoodsImg;
import com.example.backend.entity.bean.GoodsSku;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GoodsResponse {
    private Integer goodsId;
    private String goodsName;
    private Integer categoryId;
    private Integer rootCategoryId;
    private Integer soldNum;
    private String goodsTrait;
    private String instructions;
    private String material;
    private List<GoodsImg> goodsImg;
    private List<GoodsSku> goodsSku;
}
