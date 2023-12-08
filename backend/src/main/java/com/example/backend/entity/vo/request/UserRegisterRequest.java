package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

//注册传过来的请求
@Data
@AllArgsConstructor
public class UserRegisterRequest {
    private String userName;
    private String userPwd;
}
