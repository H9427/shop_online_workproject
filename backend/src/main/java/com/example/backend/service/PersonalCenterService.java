package com.example.backend.service;

import com.example.backend.entity.vo.request.UserInformationEditRequest;
import com.example.backend.entity.vo.response.UsersResponse;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.ServerException;

public interface PersonalCenterService {

    public UsersResponse queryInformation(Integer userId);

    //修改个人信息
    public UsersResponse modifyInformation(Integer userId, UserInformationEditRequest user);

    //上传头像
    public String editUserAvatar(Integer userId, MultipartFile file) throws ServerException;
}
