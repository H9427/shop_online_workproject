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
@TableName("goods_sku")
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSku {
    @TableId(type = IdType.AUTO)
    private Integer skuId;
    private Integer goodsId;
    private String skuName;
    private BigDecimal originalPrice;
    private Integer stock;
}
