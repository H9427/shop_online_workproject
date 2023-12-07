package com.example.backend.service;

import com.example.backend.entity.vo.query.UserInformationModifyQuery;
import com.example.backend.entity.vo.response.UsersVO;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.ServerException;

public interface PersonalCenterService {

    public UsersVO queryInformation(Integer userId);

    //修改个人信息
    public UsersVO modifyInformation(Integer userId, UserInformationModifyQuery user);

    //上传头像
    public String editUserAvatar(Integer userId, MultipartFile file) throws ServerException;
}
