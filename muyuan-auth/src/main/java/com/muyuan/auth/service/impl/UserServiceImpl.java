package com.muyuan.auth.service.impl;

import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.auth.dto.UserInfo;
import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.api.UserInterface;
import com.muyuan.member.interfaces.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserInterface userInterFace;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserDTO> result  = userInterFace.getUserByUsername(username);

        if (!ResultUtil.isSuccess(result)) {
            throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
        }

        log.info("用户:{},登录成功",username);
        UserDTO userDTO = result.getData();
        Set<GrantedAuthority> authorities = new HashSet<>();

        userDTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        com.muyuan.auth.dto.UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDTO,userInfo);
        userInfo.setAuthorities(authorities);
        return userInfo;
    }

}
