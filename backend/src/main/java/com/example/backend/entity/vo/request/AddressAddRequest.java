package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

//添加地址请求
@Data
@AllArgsConstructor
public class AddressAddRequest {
    private String receiverName;
    private String receiverMobile;
    private String province;//省份
    private String city;//城市
    private String area;//区县
    private String address;//详细地址
    private String postCode;//邮编
    private Integer commonAddress;//默认地址，1：是  0：否
}
