package com.example.backend.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@TableName("order_item")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    private Integer skuId;
    private BigDecimal goodsPrice;
    private Integer count;//购买数量
    private Integer isComment;//评论状态
}
