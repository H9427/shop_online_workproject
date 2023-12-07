package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.bean.Users;
import com.example.backend.entity.vo.query.UserIdentityQuery;
import com.example.backend.entity.vo.query.UserInformationModifyQuery;
import com.example.backend.entity.vo.query.UserRegisterQuery;
import com.example.backend.entity.vo.query.UserResetPwdQuery;
import com.example.backend.entity.vo.response.UsersVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.ServerException;

public interface UsersService extends IService<Users>, UserDetailsService {

    //根据用户名查找用户
    public UserDetails loadUserByUsername(String username);

    //根据用户名查找用户
    public Users findAccountByName(String username);

    //注册用户：通过账号密码注册
    public boolean RegisterUser(UserRegisterQuery userRegisterQuery);

    //通过用户名和手机号身份认证
    public boolean IdentityAuthentication(String userName,String userMobile);

    //修改密码
    public boolean ResetPassword(UserResetPwdQuery userResetPwdQuery);


}