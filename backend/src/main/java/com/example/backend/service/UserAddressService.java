package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.request.AddressAddRequest;
import com.example.backend.entity.vo.request.AddressEditRequest;
import com.example.backend.entity.vo.response.UserAddressResponse;

import java.util.List;

public interface UserAddressService extends IService<UserAddress> {

    //添加地址
    public UserAddress addAddress(Integer userId, AddressAddRequest addAddressRequest);

    //查询地址列表
    public List<UserAddressResponse> listAddress(Integer userId);

    //编辑地址信息
    public UserAddressResponse editAddress(Integer userId, AddressEditRequest addressEditRequest);
}
