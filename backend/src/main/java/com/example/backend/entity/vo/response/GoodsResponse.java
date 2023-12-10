package com.example.backend.entity.vo.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.backend.entity.bean.GoodsImg;
import com.example.backend.entity.bean.GoodsParams;
import com.example.backend.entity.bean.GoodsSku;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsResponse {
    private Integer goodsId;
    private String goodsName;
    private Integer categoryId;
    private Integer rootCategoryId;
    private Integer soldNum;
    private Integer status;
    private String context;
    private Date createTime;
    private GoodsImg goodsImg;
    private GoodsParams goodsParams;
    private GoodsSku goodsSku;
}
