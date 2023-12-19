package com.example.backend.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.CarouselChart;
import com.example.backend.entity.bean.Goods;
import com.example.backend.entity.bean.GoodsComments;
import com.example.backend.entity.bean.Users;
import com.example.backend.entity.vo.request.GoodsCommentsAddRequest;
import com.example.backend.entity.vo.request.OrdersEditStateRequest;
import com.example.backend.entity.vo.response.CategoryClassGoodsResponse;
import com.example.backend.entity.vo.response.CommentsResonse;
import com.example.backend.entity.vo.response.GoodsCommentsResponse;
import com.example.backend.entity.vo.response.GoodsResponse;
import com.example.backend.mapper.GoodsCommentsMapper;
import com.example.backend.mapper.GoodsMapper;
import com.example.backend.service.*;
import com.example.backend.utils.AliyunResource;
import com.example.backend.utils.FileResource;
import io.lettuce.core.ScriptOutputType;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GoodsCommentsServiceImpl extends ServiceImpl<GoodsCommentsMapper, GoodsComments> implements GoodsCommentsService {

    @Resource
    PersonalCenterServiceImpl personalCenterService;

    @Resource
    OrderItemService orderItemService;

    @Resource
    OrdersService ordersService;

    private final FileResource fileResource;

    private final AliyunResource aliyunResource;

    @Override
    public GoodsCommentsResponse addComments(Integer userId,GoodsCommentsAddRequest goodsCommentsAddRequest) {
        GoodsCommentsResponse goodsCommentsResponse = new GoodsCommentsResponse();

        //读入配置信息
        String endpoint = fileResource.getEndpoint();
        String accessKeyId = aliyunResource.getAccessKeyId();
        String accessKeySecret = aliyunResource.getAccessKeySecret();
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String filename = goodsCommentsAddRequest.getCommImg().getOriginalFilename();
        //分隔文件名，获得文件后缀名
        assert filename != null;
        String[] fileNameArr = filename.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        String uploadFileName = fileResource.getObjectName() + UUID.randomUUID() + "." + suffix;
        //上传网络需要的字节流
        InputStream inputStream = null;
        try {
            inputStream = goodsCommentsAddRequest.getCommImg().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //执行阿里云上传操作
        ossClient.putObject(fileResource.getBucketName(), uploadFileName, inputStream);
        //关闭ossClient
        ossClient.shutdown();
        uploadFileName = fileResource.getOssHost() + uploadFileName;

        try{
            GoodsComments goodsComments = new GoodsComments(
                    null,
                    goodsCommentsAddRequest.getGoodsId(),
                    userId,
                    goodsCommentsAddRequest.getIsAnonymous(),
                    goodsCommentsAddRequest.getCommType(),
                    goodsCommentsAddRequest.getCommContent(),
                    uploadFileName,
                    new Date()
            );
            baseMapper.insert(goodsComments);
            //修改评论状态
            orderItemService.setIsComment(goodsCommentsAddRequest.getOrderItemId());
            //查看是否还有未评论
            if(orderItemService.queryIsComment(goodsCommentsAddRequest.getOrderId())){
                OrdersEditStateRequest ordersEditStateRequest = new OrdersEditStateRequest();
                ordersEditStateRequest.setOrderId(goodsCommentsAddRequest.getOrderId());
                ordersEditStateRequest.setState(4);
                ordersService.editState(ordersEditStateRequest);
            }
            goodsCommentsResponse.setId(goodsComments.getId());
            goodsCommentsResponse.setGoodsId(goodsComments.getGoodsId());
            goodsCommentsResponse.setUserId(goodsComments.getUserId());
            goodsCommentsResponse.setIsAnonymous(goodsComments.getIsAnonymous());
            goodsCommentsResponse.setCommType(goodsComments.getCommType());
            goodsCommentsResponse.setCommContent(goodsComments.getCommContent());
            goodsCommentsResponse.setCommImg(goodsComments.getCommImg());
            goodsCommentsResponse.setCommTime(goodsComments.getCommTime());
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return goodsCommentsResponse;
    }

    @Override
    public List<CommentsResonse> allComments(Integer goodsId) {
        List<CommentsResonse> commentsResonses = new ArrayList<>();
        try{
            LambdaQueryWrapper<GoodsComments> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(GoodsComments::getGoodsId,goodsId);
            wrapper.orderByDesc(GoodsComments::getCommTime);
            List<GoodsComments> goodsComments = baseMapper.selectList(wrapper);
            for (GoodsComments goodsComment:goodsComments){
                CommentsResonse commentsResonse = new CommentsResonse();
                commentsResonse.setId(goodsComment.getId());
                commentsResonse.setGoodsId(goodsComment.getGoodsId());
                commentsResonse.setUserId(goodsComment.getUserId());
                commentsResonse.setIsAnonymous(goodsComment.getIsAnonymous());
                commentsResonse.setCommType(goodsComment.getCommType());
                commentsResonse.setCommContent(goodsComment.getCommContent());
                commentsResonse.setCommImg(goodsComment.getCommImg());
                commentsResonse.setCommTime(goodsComment.getCommTime());
                commentsResonse.setUser(personalCenterService.queryInformation(goodsComment.getUserId()));
                commentsResonses.add(commentsResonse);
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return commentsResonses;
    }
}
