package com.example.backend.entity.vo.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class GoodsCommentsAddRequest {
    private Integer goodsId;
    private Integer isAnonymous;
    private Integer commType;
    private String commContent;
    private MultipartFile commImg;
}
