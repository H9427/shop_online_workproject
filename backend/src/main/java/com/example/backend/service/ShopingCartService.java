package com.example.backend.service;

import com.example.backend.entity.vo.request.ShopingCartAddRequest;
import com.example.backend.entity.vo.request.ShopingCartDeleteRequest;
import com.example.backend.entity.vo.response.ShopingCartResponse;

import java.util.List;

public interface ShopingCartService {
    public boolean addShopingCart(Integer userId,ShopingCartAddRequest shopingCartAddRequest);

    public List<ShopingCartResponse> listShopingCart(Integer userId);

    public boolean deleteShopingCart(ShopingCartDeleteRequest shopingCartDeleteRequest);
}
