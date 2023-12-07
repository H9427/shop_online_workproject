package com.example.backend.entity.vo.query;

import lombok.AllArgsConstructor;
import lombok.Data;

//注册传过来的请求
@Data
@AllArgsConstructor
public class UserRegisterQuery {
    private String userName;
    private String userPwd;
}
