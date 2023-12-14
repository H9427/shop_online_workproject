package com.example.backend.service;

import com.example.backend.entity.vo.request.ShopingCartAddRequest;

public interface ShopingCartService {
    public boolean addShopingCart(Integer userId,ShopingCartAddRequest shopingCartAddRequest);
}
