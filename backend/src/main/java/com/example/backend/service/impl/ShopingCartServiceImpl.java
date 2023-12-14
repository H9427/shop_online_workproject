package com.example.backend.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.ShopingCart;
import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.request.ShopingCartAddRequest;
import com.example.backend.mapper.ShopingCartMapper;
import com.example.backend.mapper.UserAddressMapper;
import com.example.backend.service.ShopingCartService;
import com.example.backend.service.UserAddressService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShopingCartServiceImpl extends ServiceImpl<ShopingCartMapper, ShopingCart> implements ShopingCartService {
    @Override
    public boolean addShopingCart(Integer userId,ShopingCartAddRequest shopingCartAddRequest) {
        try{
            ShopingCart shopingCart = new ShopingCart();
            shopingCart.setGoodsId(shopingCartAddRequest.getGoodsId());
            shopingCart.setSkuId(shopingCartAddRequest.getSkuId());
            shopingCart.setUserId(userId);
            shopingCart.setCartNum(shopingCartAddRequest.getCartNum());
            shopingCart.setGoodsPrice(shopingCartAddRequest.getGoodsPrice());
            shopingCart.setCartTime(new Date());
            System.out.println(shopingCart);
            baseMapper.insert(shopingCart);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
