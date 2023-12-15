package com.example.backend.entity.vo.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShopingCartAddRequest {
    private Integer goodsId;
    private Integer skuId;
    private Integer cartNum;
    private BigDecimal goodsPrice;
}
