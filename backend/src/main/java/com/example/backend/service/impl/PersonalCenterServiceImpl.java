package com.example.backend.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Users;
import com.example.backend.entity.vo.request.UserInformationEditRequest;
import com.example.backend.entity.vo.response.UsersResponse;
import com.example.backend.mapper.UsersMapper;
import com.example.backend.service.PersonalCenterService;
import com.example.backend.utils.AliyunResource;
import com.example.backend.utils.FileResource;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;
import java.util.UUID;


@Service
@AllArgsConstructor
public class PersonalCenterServiceImpl extends ServiceImpl<UsersMapper, Users> implements PersonalCenterService {

    private final FileResource fileResource;

    private final AliyunResource aliyunResource;

    @Override
    public UsersResponse queryInformation(Integer userId) {
        Users users = query().eq("id",userId).one();
        UsersResponse usersVO = new UsersResponse();
        usersVO.setId(users.getId());
        usersVO.setUserName(users.getUserName());
        usersVO.setUserPwd(users.getUserPwd());
        usersVO.setNickName(users.getNickName());
        usersVO.setRealName(users.getRealName());
        usersVO.setUserImg(users.getUserImg());
        usersVO.setUserMobile(users.getUserMobile());
        usersVO.setUserSex(users.getUserSex());
        usersVO.setUserRegtime(users.getUserRegtime());
        return usersVO;
    }

    @Override
    public UsersResponse modifyInformation(Integer userId, UserInformationEditRequest user) {
        Users user1 = query().eq("id",userId).one();
        if(user.getUserPwd() != "") {
            String Pwd = new BCryptPasswordEncoder().encode(user.getUserPwd());
            user1.setUserPwd(Pwd);
        }
        user1.setNickName(user.getNickName());
        user1.setRealName(user.getRealName());
        user1.setUserMobile(user.getUserMobile());
        user1.setUserSex(user.getUserSex());
        baseMapper.updateById(user1);
        UsersResponse usersVO = new UsersResponse();
        BeanUtils.copyProperties(user1,usersVO);
        return usersVO;
    }

    @Override
    public String editUserAvatar(Integer userId, MultipartFile file) throws ServerException {
        //读入配置信息
        String endpoint = fileResource.getEndpoint();
        String accessKeyId = aliyunResource.getAccessKeyId();
        String accessKeySecret = aliyunResource.getAccessKeySecret();
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String filename = file.getOriginalFilename();
        //分隔文件名，获得文件后缀名
        assert filename != null;
        String[] fileNameArr = filename.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        String uploadFileName = fileResource.getObjectName() + UUID.randomUUID() + "." + suffix;
        //上传网络需要的字节流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //执行阿里云上传操作
        ossClient.putObject(fileResource.getBucketName(), uploadFileName, inputStream);
        //关闭ossClient
        ossClient.shutdown();
        //修改用户头像
        Users user = baseMapper.selectById(userId);
        if (user == null) {
            throw new ServerException("用户不存在");
        }
        uploadFileName = fileResource.getOssHost() + uploadFileName;
        user.setUserImg(uploadFileName);
        baseMapper.updateById(user);
        return uploadFileName;
    }
}
