package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.convert.AddressConvert;
import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.request.AddressAddRequest;
import com.example.backend.entity.vo.request.AddressCommonRequest;
import com.example.backend.entity.vo.request.AddressDeleteRequest;
import com.example.backend.entity.vo.request.AddressEditRequest;
import com.example.backend.entity.vo.response.UserAddressResponse;
import com.example.backend.mapper.UserAddressMapper;
import com.example.backend.service.UserAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {
    @Override
    public UserAddress addAddress(Integer userId, AddressAddRequest addAddressRequest) {
        if(addAddressRequest.getCommonAddress() == 1&&query().eq("user_id",userId).eq("common_address",1).one() != null){
            return null;
        }
        UserAddress userAddress = new UserAddress();
        userAddress.setAddressId(null);
        userAddress.setUserId(userId);
        userAddress.setReceiverName(addAddressRequest.getReceiverName());
        userAddress.setReceiverMobile(addAddressRequest.getReceiverMobile());
        userAddress.setProvince(addAddressRequest.getProvince());
        userAddress.setCity(addAddressRequest.getCity());
        userAddress.setArea(addAddressRequest.getArea());
        userAddress.setAddress(addAddressRequest.getAddress());
        userAddress.setPostCode(addAddressRequest.getPostCode());
        userAddress.setDeleteFlag(1);
        userAddress.setCommonAddress(addAddressRequest.getCommonAddress());
        baseMapper.insert(userAddress);
        return userAddress;
    }

    @Override
    public List<UserAddressResponse> listAddress(Integer userId) {
        LambdaQueryWrapper<UserAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAddress::getUserId, userId);
        wrapper.eq(UserAddress::getDeleteFlag,1);
//        根据是否为默认地址和创建时间倒序排列
        wrapper.orderByDesc(UserAddress::getCommonAddress);
        List<UserAddress> userAddresses = baseMapper.selectList(wrapper);
        List<UserAddressResponse> results = AddressConvert.convertToAddressResponseList(userAddresses);
        return results;
    }

    @Override
    public UserAddressResponse editAddress(Integer userId, AddressEditRequest addressEditRequest) {
        UserAddress userAddress = query().eq("address_id", addressEditRequest.getAddressId()).one();
        userAddress.setReceiverName(addressEditRequest.getReceiverName());
        userAddress.setReceiverMobile(addressEditRequest.getReceiverMobile());
        userAddress.setProvince(addressEditRequest.getProvince());
        userAddress.setCity(addressEditRequest.getCity());
        userAddress.setArea(addressEditRequest.getArea());
        userAddress.setAddress(addressEditRequest.getAddress());
        userAddress.setPostCode(addressEditRequest.getPostCode());
        baseMapper.updateById(userAddress);
        UserAddressResponse results = AddressConvert.convertToAddressResponse(userAddress);
        return results;
    }

    @Override
    public boolean deleteAddress(Integer userId, AddressDeleteRequest addressDeleteRequest) {
        UserAddress userAddress = query().eq("user_id", userId).eq("address_id", addressDeleteRequest.getAddressId()).one();
        if(userAddress != null){
            userAddress.setDeleteFlag(0);
            baseMapper.updateById(userAddress);
            return true;
        }
        return false;
    }

    @Override
    public Integer getDefaultAddress(Integer userId) {
        UserAddress userAddress = query().eq("user_id", userId).eq("common_address",1).one();
        if(userAddress == null){
            return 0;
        }
        return userAddress.getAddressId();
    }

    @Override
    public Boolean commonAddress(Integer userId, AddressCommonRequest addressCommonRequest) {
        try{
            UserAddress userAddress = query().eq("user_id", userId).eq("common_address", 1).one();
            userAddress.setCommonAddress(0);
            baseMapper.updateById(userAddress);
            UserAddress userAddress1 = query().eq("user_id", userId).eq("address_id", addressCommonRequest.getAddressId()).one();
            userAddress1.setCommonAddress(1);
            baseMapper.updateById(userAddress1);
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
