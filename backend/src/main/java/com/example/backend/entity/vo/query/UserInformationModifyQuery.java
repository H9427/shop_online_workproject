package com.example.backend.entity.vo.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserInformationModifyQuery {
    private Integer id;
    private String userName;
    private String userPwd;
    private String nickName;
    private String realName;
    private String userImg;
    private String userMobile;
    private String userSex;
}
