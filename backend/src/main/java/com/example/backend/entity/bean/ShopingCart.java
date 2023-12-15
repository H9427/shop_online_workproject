package com.example.backend.entity.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("shoping_cart")
@AllArgsConstructor
@NoArgsConstructor
public class ShopingCart {
    @TableId(type = IdType.AUTO)
    private Integer cartId;
    private Integer goodsId;
    private Integer skuId;
    private Integer userId;
    private Integer cartNum;
    private BigDecimal goodsPrice;
    private Date cartTime;
}
