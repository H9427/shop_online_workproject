package com.example.backend.service;

import com.example.backend.entity.vo.request.FavoriteAddRequest;
import com.example.backend.entity.vo.response.FavoreteResponse;

import java.util.List;

public interface FavoriteService {

    public boolean addFavorite(Integer userId, FavoriteAddRequest favoriteAddRequest);

    public List<FavoreteResponse> queryAllFavorete(Integer userId);
}
