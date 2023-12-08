package com.example.backend.convert;

import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.request.AddressAddRequest;
import com.example.backend.entity.vo.response.UserAddressResponse;

import java.util.ArrayList;
import java.util.List;


public class AddressConvert {

    public static UserAddress convert(AddressAddRequest addressVO){
        if ( addressVO == null ) {
            return null;
        }

        UserAddress userAddress = new UserAddress();
        userAddress.setReceiverName( addressVO.getReceiverName() );
        userAddress.setReceiverMobile( addressVO.getReceiverMobile() );
        userAddress.setProvince( addressVO.getProvince() );
        userAddress.setCity( addressVO.getCity() );
        userAddress.setProvince( addressVO.getProvince() );
        userAddress.setCity( addressVO.getCity() );
        userAddress.setArea( addressVO.getArea() );
        userAddress.setAddress( addressVO.getAddress() );
        userAddress.setPostCode( addressVO.getPostCode() );
        userAddress.setCommonAddress( addressVO.getCommonAddress() );
        return userAddress;
    }


    public static List<UserAddressResponse> convertToAddressResponseList(List<UserAddress> addressList){
        if ( addressList == null ) {
            return null;
        }

        List<UserAddressResponse> list = new ArrayList<UserAddressResponse>( addressList.size() );
        for ( UserAddress userAddress : addressList ) {
            list.add( convertToAddressResponse( userAddress ) );
        }

        return list;
    }


    public static UserAddressResponse convertToAddressResponse(UserAddress userAddress){
        if ( userAddress == null ) {
            return null;
        }

        UserAddressResponse userAddressResponse = new UserAddressResponse();

        userAddressResponse.setAddressId(userAddress.getAddressId());
        userAddressResponse.setUserId(userAddress.getUserId());
        userAddressResponse.setReceiverName(userAddress.getReceiverName());
        userAddressResponse.setReceiverMobile(userAddress.getReceiverMobile());
        userAddressResponse.setProvince(userAddress.getProvince());
        userAddressResponse.setCity(userAddress.getCity());
        userAddressResponse.setArea(userAddress.getArea());
        userAddressResponse.setAddress(userAddress.getAddress());
        userAddressResponse.setPostCode(userAddress.getPostCode());
        userAddressResponse.setDeleteFlag(userAddressResponse.getDeleteFlag());
        userAddressResponse.setCommonAddress(userAddress.getCommonAddress());
        return userAddressResponse;
    }
}
