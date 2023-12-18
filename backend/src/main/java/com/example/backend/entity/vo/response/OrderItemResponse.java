package com.example.backend.entity.vo.response;

import com.example.backend.entity.bean.GoodsSku;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemResponse {
    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    private Integer skuId;
    private BigDecimal goodsPrice;
    private Integer count;//购买数量
    private Integer isComment;//评论状态
    private GoodsResponse goods;
    private GoodsSku sku;
}
