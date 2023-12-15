package com.example.backend.entity.vo.response;

import com.example.backend.entity.bean.GoodsSku;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShopingCartResponse {
    private Integer cartId;
    private Integer goodsId;
    private Integer userId;
    private Integer cartNum;
    private BigDecimal goodsPrice;
    private GoodsSku goodsSku;
    private CategoryClassGoodsResponse goodsDetails;
    private Date cartTime;
}
