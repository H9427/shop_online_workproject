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
@TableName("orders")
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer addressId;
    private BigDecimal totalAmount;
    private String orderRemark;
    private Integer status;
    private Integer deliveryFlowId;//物流单号
    private Date createTime;//创建时间
    private Date payTime;//付款时间
    private Date deliveryTime;//发货时间
    private Date flishTime;//完成时间
    private Date cancelTime;//取消时间
}
