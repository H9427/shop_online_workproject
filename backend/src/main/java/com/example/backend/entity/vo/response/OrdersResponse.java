package com.example.backend.entity.vo.response;

import com.example.backend.entity.bean.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersResponse {
    private Integer id;
    private Integer userId;
    private Integer addressId;
    private BigDecimal totalAmount;
    private String orderRemark;
    private Integer status;
    private Integer deliveryFlowId;//物流单号
    private Date createTime;//创建时间
    private Date payTime;//付款时间
    private Date flishTime;//完成时间
    private List<OrderItemResponse> items;
}
