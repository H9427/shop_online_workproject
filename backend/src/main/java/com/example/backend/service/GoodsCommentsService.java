package com.example.backend.service;

import com.example.backend.entity.vo.request.GoodsCommentsAddNoPicRequest;
import com.example.backend.entity.vo.request.GoodsCommentsAddRequest;
import com.example.backend.entity.vo.response.CategoryClassGoodsResponse;
import com.example.backend.entity.vo.response.CommentsResonse;
import com.example.backend.entity.vo.response.GoodsCommentsResponse;
import com.example.backend.entity.vo.response.GoodsResponse;

import java.util.List;

public interface GoodsCommentsService {

    public GoodsCommentsResponse addComments(Integer userId,GoodsCommentsAddRequest goodsCommentsAddRequest);

    public GoodsCommentsResponse addCommentsNoPic(Integer userId, GoodsCommentsAddNoPicRequest goodsCommentsAddRequest);

    public List<CommentsResonse> allComments(Integer goodsId);


}
