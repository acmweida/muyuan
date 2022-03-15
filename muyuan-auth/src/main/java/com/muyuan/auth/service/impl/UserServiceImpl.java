package com.muyuan.auth.service.impl;

import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.auth.dto.SysUserInfo;
import com.muyuan.auth.dto.UserInfo;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.UserType;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.member.api.UserInterface;
import com.muyuan.member.interfaces.dto.UserDTO;
import com.muyuan.system.api.SysUserInterface;
import com.muyuan.system.interfaces.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
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

    @Reference(group = ServiceTypeConst.MEMBER_SERVICE, version = "1.0")
    UserInterface userInterFace;

    @Reference(group = ServiceTypeConst.SYSTEM_SERVICE, version = "1.0")
    SysUserInterface sysUserInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserDTO> result = userInterFace.getUserByUsername(username);

        if (!ResultUtil.isSuccess(result)) {
            throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
        }

        log.info("用户:{},登录成功", username);
        UserDTO userDTO = result.getData();
        Set<GrantedAuthority> authorities = new HashSet<>();

        userDTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        com.muyuan.auth.dto.UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDTO, userInfo);
        userInfo.setAuthorities(authorities);
        return userInfo;
    }

    /**
     * 登录
     *
     * @param username
     * @param userType 用户类型
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username, String userType) throws UsernameNotFoundException {
        if (UserType.MEMBER.name().equals(userType)) {
            Result<UserDTO> result = userInterFace.getUserByUsername(username);
            if (!ResultUtil.isSuccess(result)) {
                throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
            }
            log.info("商家用户:{},登录成功", username);
            UserDTO userDTO = result.getData();
            Set<GrantedAuthority> authorities = new HashSet<>();

            userDTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userDTO, userInfo);
            userInfo.setAuthorities(authorities);
            return userInfo;

        } else if (UserType.SYSUSER.name().equals(userType)) {
            Result<SysUserDTO> result = sysUserInterface.getUserByUsername(username);
            if (!ResultUtil.isSuccess(result)) {
                throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
            }

            log.info("管理用户:{},登录成功", username);
            SysUserDTO userDTO = result.getData();
            Set<GrantedAuthority> authorities = new HashSet<>();

            userDTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

            SysUserInfo userInfo = new SysUserInfo();
            BeanUtils.copyProperties(userDTO, userInfo);
            userInfo.setAuthorities(authorities);
            return userInfo;
        } else {
            log.info("user_type:[{}] not found",userType);
            throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
        }

    }

}
