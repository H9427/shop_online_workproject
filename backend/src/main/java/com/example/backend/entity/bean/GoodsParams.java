package com.example.backend.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//商品参数
@Data
@TableName("goods_params")
@AllArgsConstructor
@NoArgsConstructor
public class GoodsParams {
    @TableId(type = IdType.AUTO)
    private Integer paramId;
    private Integer goodsId;//产品id
    private String goodsPlace;//产地
    private String footPeriod;//保质期
    private String brand;//品牌名
    private String factoryName;//生产厂名
    private String factoryAddress;//生产厂址
    private String weight;//规格重量
    private Date createdTime;//创建时间
}
