package com.example.backend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInformationEditRequest {
    private String userPwd;
    private String nickName;
    private String realName;
    private String userMobile;
    private String userSex;
}
