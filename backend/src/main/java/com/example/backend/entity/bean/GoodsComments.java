package com.example.backend.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("goods_comments")
@AllArgsConstructor
@NoArgsConstructor
public class GoodsComments {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer goodsId;
    private Integer userId;
    private Integer isAnonymous;
    private Integer commType;
    private String commContent;
    private String commImg;
    private Date commTime;
}
