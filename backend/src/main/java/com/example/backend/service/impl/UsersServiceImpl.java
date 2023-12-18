package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Users;
import com.example.backend.entity.vo.request.UserRegisterRequest;
import com.example.backend.entity.vo.request.UserResetPwdRequest;
import com.example.backend.mapper.UsersMapper;
import com.example.backend.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public boolean registerUser(UserRegisterRequest userRegisterQuery) {
        if((query().eq("user_name",userRegisterQuery.getUserName()).one()) == null){
            String Pwd = new BCryptPasswordEncoder().encode(userRegisterQuery.getUserPwd());
            Users users = new Users(null,
                    userRegisterQuery.getUserName(),
                    Pwd,
                    "游客" + (int)(Math.random() * 10000000 + 10000000),
                    null,
                    "https://hbimg.huabanimg.com/07b029c67010c0a17a1c78fcbc92ce612de4cf3ae8dd-Oc4KXC_fw658",
                    null,
                    null,
                    new Date());
            baseMapper.insert(users);
            return true;
        }
        return false;
    }

    @Override
    public boolean identityAuthentication(String userName,String userMobile) {
        Users users = query().eq("user_name",userName)
                .eq("user_mobile",userMobile)
                .one();
        if(users != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean resetPassword(UserResetPwdRequest userResetPwdVO) {
        try{
            Users users =  query().eq("user_name", userResetPwdVO.getUserName()).one();
            String Pwd = new BCryptPasswordEncoder().encode(userResetPwdVO.getUserPwd());
            users.setUserPwd(Pwd);
            baseMapper.update(users,new LambdaQueryWrapper<Users>().eq(Users::getUserName, userResetPwdVO.getUserName()));
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
