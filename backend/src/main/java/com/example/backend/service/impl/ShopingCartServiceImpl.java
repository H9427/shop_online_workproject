package com.example.backend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Goods;
import com.example.backend.entity.bean.ShopingCart;
import com.example.backend.entity.bean.UserAddress;
import com.example.backend.entity.vo.request.ShopingCartAddRequest;
import com.example.backend.entity.vo.request.ShopingCartDeleteRequest;
import com.example.backend.entity.vo.response.AllItemsAndPricesResponse;
import com.example.backend.entity.vo.response.ShopingCartResponse;
import com.example.backend.mapper.ShopingCartMapper;
import com.example.backend.mapper.UserAddressMapper;
import com.example.backend.service.GoodsService;
import com.example.backend.service.GoodsSkuService;
import com.example.backend.service.ShopingCartService;
import com.example.backend.service.UserAddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopingCartServiceImpl extends ServiceImpl<ShopingCartMapper, ShopingCart> implements ShopingCartService {

    @Resource
    GoodsSkuService goodsSkuService;

    @Resource
    GoodsService goodsService;

    @Override
    public boolean addShopingCart(Integer userId,ShopingCartAddRequest shopingCartAddRequest) {
        try{
            ShopingCart shopingCart1 = query().eq("goods_id", shopingCartAddRequest.getGoodsId()).
                    eq("sku_id", shopingCartAddRequest.getSkuId()).
                    eq("user_id", userId).one();

            if(shopingCart1 == null){
                ShopingCart shopingCart = new ShopingCart();
                shopingCart.setGoodsId(shopingCartAddRequest.getGoodsId());
                shopingCart.setSkuId(shopingCartAddRequest.getSkuId());
                shopingCart.setUserId(userId);
                shopingCart.setCartNum(shopingCartAddRequest.getCartNum());
                shopingCart.setGoodsPrice(shopingCartAddRequest.getGoodsPrice());
                shopingCart.setCartTime(new Date());
                baseMapper.insert(shopingCart);
            }else {
                shopingCart1.setCartNum(shopingCart1.getCartNum() + shopingCartAddRequest.getCartNum());
                baseMapper.updateById(shopingCart1);
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public List<ShopingCartResponse> listShopingCart(Integer userId) {
        LambdaQueryWrapper<ShopingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShopingCart::getUserId, userId);
        wrapper.orderByDesc(ShopingCart::getCartTime);
        List<ShopingCart> shopingCarts = baseMapper.selectList(wrapper);
        List<ShopingCartResponse> shopingCartResponses = new ArrayList<>();
        for (ShopingCart shopingCart : shopingCarts){
            ShopingCartResponse shopingCartResponse = new ShopingCartResponse();
            shopingCartResponse.setCartId(shopingCart.getCartId());
            shopingCartResponse.setGoodsId(shopingCart.getGoodsId());
            shopingCartResponse.setUserId(shopingCart.getUserId());
            shopingCartResponse.setCartNum(shopingCart.getCartNum());
            shopingCartResponse.setGoodsPrice(shopingCart.getGoodsPrice());
            shopingCartResponse.setGoodsSku(goodsSkuService.getGoodsById(shopingCart.getSkuId()));
            shopingCartResponse.setGoodsDetails(goodsService.getGoodsById(shopingCart.getGoodsId()));
            shopingCartResponse.setCartTime(shopingCart.getCartTime());
            shopingCartResponses.add(shopingCartResponse);
        }
        return shopingCartResponses;
    }

    @Override
    public boolean deleteShopingCart(ShopingCartDeleteRequest shopingCartDeleteRequest) {
        List<Integer> cartIds = shopingCartDeleteRequest.getCartIds();
        for (Integer cartId:cartIds){
            baseMapper.deleteById(cartId);
        }

        return true;
    }

    @Override
    public AllItemsAndPricesResponse allItemsAndPrices(Integer userId) {
        AllItemsAndPricesResponse allItemsAndPricesResponse = new AllItemsAndPricesResponse();
        try {
            LambdaQueryWrapper<ShopingCart> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ShopingCart::getUserId, userId);
            List<ShopingCart> shopingCarts = baseMapper.selectList(wrapper);
            Integer count = 0;
            BigDecimal prices = BigDecimal.valueOf(0.0);
            for (ShopingCart shopingCart:shopingCarts){
                count+= 1;
                prices = prices.add(shopingCart.getGoodsPrice().multiply(BigDecimal.valueOf(shopingCart.getCartNum())));
            }
            allItemsAndPricesResponse.setItemsCount(count);
            allItemsAndPricesResponse.setPrices(prices);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return allItemsAndPricesResponse;
    }
}
