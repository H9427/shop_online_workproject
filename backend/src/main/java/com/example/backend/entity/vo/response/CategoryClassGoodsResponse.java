package com.example.backend.entity.vo.response;

import com.example.backend.entity.bean.GoodsImg;
import com.example.backend.entity.bean.GoodsSku;
import lombok.Data;


@Data
public class CategoryClassGoodsResponse {
    private Integer goodsId;
    private String goodsName;
    private Integer categoryId;
    private Integer rootCategoryId;
    private Integer soldNum;
    private String goodsTrait;
    private String instructions;
    private String material;
    private GoodsImg goodsImg;
    private GoodsSku goodsSku;
}
