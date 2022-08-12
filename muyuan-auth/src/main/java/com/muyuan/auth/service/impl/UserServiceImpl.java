package com.muyuan.auth.service.impl;

import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.auth.dto.SysUserInfo;
import com.muyuan.auth.dto.UserInfo;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.UserType;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.menager.api.SysUserInterface;
import com.muyuan.menager.interfaces.to.SysUserTO;
import com.muyuan.store.api.UserInterface;
import com.muyuan.store.interfaces.to.UserTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
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

    @DubboReference(group = ServiceTypeConst.MEMBER_SERVICE, version = "1.0")
    UserInterface userInterFace;

    @DubboReference(group = ServiceTypeConst.SYSTEM_SERVICE, version = "1.0")
    SysUserInterface sysUserInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserTO> result = userInterFace.getUserByUsername(username);

        if (!ResultUtil.isSuccess(result)) {
            throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
        }

        log.info("用户:{},登录成功", username);
        UserTO userTO = result.getData();
        Set<GrantedAuthority> authorities = new HashSet<>();

        userTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userTO, userInfo);
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
        if (UserType.STORE.name().equals(userType)) {
            Result<UserTO> result = userInterFace.getUserByUsername(username);
            if (!ResultUtil.isSuccess(result)) {
                throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
            }
            log.info("商家用户:{},登录", username);
            UserTO userTO = result.getData();
            Set<GrantedAuthority> authorities = new HashSet<>();

            if (ObjectUtils.isNotEmpty(userTO.getRoles())) {
                userTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
            }

            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userTO, userInfo);
            userInfo.setAuthorities(authorities);
            return userInfo;

        } else if (UserType.SYSUSER.name().equals(userType)) {
            Result<SysUserTO> result = sysUserInterface.getUserByUsername(username);
            if (!ResultUtil.isSuccess(result)) {
                throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
            }

            log.info("管理用户:{},登录", username);
            SysUserTO userDTO = result.getData();
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
