package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.response.UserAddressVO;

public interface UserAddressService extends IService<UserAddress> {

    //添加地址
    public UserAddressVO AddAddress()
}
