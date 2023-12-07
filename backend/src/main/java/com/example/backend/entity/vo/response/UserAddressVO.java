package com.example.backend.entity.vo.response;

import lombok.Data;

@Data
public class UserAddressVO {
    private Integer addressId;
    private Integer userId;
    private String receiverName;
    private String receiverMobile;
    private String province;//省份
    private String city;//城市
    private String area;//区县
    private String address;//详细地址
    private String postCode;//邮编
    private Integer deleteFlag;//逻辑删除，1：正常  0：无效
    private Integer commonAddress;//默认地址，1：是  0：否
}
