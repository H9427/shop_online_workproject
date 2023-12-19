package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.CarouselChart;
import com.example.backend.entity.bean.Category;
import com.example.backend.entity.bean.Favorite;
import com.example.backend.entity.vo.request.FavoriteAddRequest;
import com.example.backend.entity.vo.request.FavoriteCancelRequest;
import com.example.backend.entity.vo.response.FavoreteResponse;
import com.example.backend.mapper.CarouselChartMapper;
import com.example.backend.mapper.FavoriteMapper;
import com.example.backend.service.CarouselChartService;
import com.example.backend.service.FavoriteService;
import com.example.backend.service.GoodsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {

    @Resource
    GoodsService goodsService;

    @Override
    public boolean addFavorite(Integer userId, FavoriteAddRequest favoriteAddRequest) {
        try{
            Favorite favorite = new Favorite(null,userId, favoriteAddRequest.getGoodsId());
            baseMapper.insert(favorite);
        }catch (Exception e){
            System.out.println(false);
        }
        return true;
    }

    @Override
    public List<FavoreteResponse> queryAllFavorete(Integer userId) {
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getUserId, userId);
        wrapper.orderByAsc(Favorite::getId);
        List<Favorite> favoriteList = baseMapper.selectList(wrapper);
        List<FavoreteResponse> favoreteResponseList = new ArrayList<>();
        for(Favorite favorite:favoriteList){
            FavoreteResponse favoreteResponse = new FavoreteResponse();
            favoreteResponse.setId(favorite.getId());
            favoreteResponse.setUserId(favorite.getUserId());
            favoreteResponse.setGoodsId(favorite.getGoodsId());
            favoreteResponse.setGoodsResponse(goodsService.goodsDetails(favorite.getGoodsId()));
            favoreteResponseList.add(favoreteResponse);
        }
        return favoreteResponseList;
    }

    @Override
    public boolean cancelFavorite(FavoriteCancelRequest favoriteCancelRequest) {
        try{
            Favorite favorite = query().eq("id", favoriteCancelRequest.getFavoriteId()).one();
            baseMapper.deleteById(favorite);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
