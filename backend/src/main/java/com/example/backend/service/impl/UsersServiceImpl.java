package com.example.backend.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Users;
import com.example.backend.entity.vo.query.UserInformationModifyQuery;
import com.example.backend.entity.vo.query.UserRegisterQuery;
import com.example.backend.entity.vo.query.UserResetPwdQuery;
import com.example.backend.entity.vo.response.UsersVO;
import com.example.backend.mapper.UsersMapper;
import com.example.backend.service.UsersService;
import com.example.backend.utils.AliyunResource;
import com.example.backend.utils.FileResource;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public boolean RegisterUser(UserRegisterQuery userRegisterQuery) {
        if((query().eq("user_name",userRegisterQuery.getUserName()).one()) == null){
            String Pwd = new BCryptPasswordEncoder().encode(userRegisterQuery.getUserPwd());
            Users users = new Users(null,userRegisterQuery.getUserName(),Pwd,null,null,"https://hbimg.huabanimg.com/07b029c67010c0a17a1c78fcbc92ce612de4cf3ae8dd-Oc4KXC_fw658",null,null,new Date());
            baseMapper.insert(users);
            return true;
        }
        return false;
    }

    @Override
    public boolean IdentityAuthentication(String userName,String userMobile) {
        Users users = query().eq("user_name",userName)
                .eq("user_mobile",userMobile)
                .one();
        if(users != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean ResetPassword(UserResetPwdQuery userResetPwdQuery) {
        try{
            Users users =  query().eq("user_name",userResetPwdQuery.getUserName()).one();
            String Pwd = new BCryptPasswordEncoder().encode(userResetPwdQuery.getUserPwd());
            users.setUserPwd(Pwd);
            baseMapper.update(users,new LambdaQueryWrapper<Users>().eq(Users::getUserName, userResetPwdQuery.getUserName()));
        }catch (Exception e){
            return false;
        }
        return true;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = this.findAccountByName(username);
        if(users == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User
                .withUsername(username)
                .password(users.getUserPwd())
                .build();
    }

    public Users findAccountByName(String username){
        return query()
                .eq("user_name",username)
                .one();
    }
}
