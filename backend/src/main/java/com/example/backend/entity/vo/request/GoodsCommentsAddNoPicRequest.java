package com.example.backend.entity.vo.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GoodsCommentsAddNoPicRequest {
    private Integer orderId;
    private Integer goodsId;
    private Integer isAnonymous;
    private Integer commType;
    private String commContent;
    private Integer orderItemId;
}
