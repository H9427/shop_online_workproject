package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class UsersResponse {
    private Integer id;
    private String userName;
    private String userPwd;
    private String nickName;
    private String realName;
    private String userImg;
    private String userMobile;
    private String userSex;
    private Date userRegtime;
}
