package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

//修改密码传递的参数
@Data
@AllArgsConstructor
public class UserResetPwdRequest {
    private String userName;
    private String userPwd;
}
