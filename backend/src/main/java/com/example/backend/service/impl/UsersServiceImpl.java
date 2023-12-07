package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.bean.Users;
import com.example.backend.entity.vo.query.UserIdentityQuery;
import com.example.backend.entity.vo.query.UserInformationModifyQuery;
import com.example.backend.entity.vo.query.UserRegisterQuery;
import com.example.backend.entity.vo.query.UserResetPwdQuery;
import com.example.backend.entity.vo.response.UsersVO;
import com.example.backend.mapper.UsersMapper;
import com.example.backend.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
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
    public boolean IdentityAuthentication(UserIdentityQuery userIdentityQuery) {
        Users users = query().eq("user_name",userIdentityQuery.getUserName())
                .eq("user_mobile",userIdentityQuery.getUserMobile())
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
    public UsersVO QueryInformation(String userName) {
        Users users = query().eq("user_name",userName).one();
        UsersVO usersVO = new UsersVO();
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
    public UsersVO ModifyInformation(UserInformationModifyQuery user) {
        Users user1 = query().eq("user_name",user.getUserName()).one();
        String Pwd = new BCryptPasswordEncoder().encode(user.getUserPwd());
        user1.setUserPwd(Pwd);
        user1.setNickName(user.getNickName());
        user1.setRealName(user.getRealName());
        user1.setUserImg(user.getUserImg());
        user1.setUserMobile(user.getUserMobile());
        user1.setUserSex(user.getUserSex());
        baseMapper.updateById(user1);
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(user1,usersVO);
        return usersVO;
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
