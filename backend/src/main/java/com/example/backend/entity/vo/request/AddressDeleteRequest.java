package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

//删除地址请求
@Data
@AllArgsConstructor
public class AddressDeleteRequest {
    private Integer addressId;
}
