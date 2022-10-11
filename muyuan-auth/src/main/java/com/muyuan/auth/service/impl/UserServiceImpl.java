package com.muyuan.auth.service.impl;

import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.auth.dto.User;
import com.muyuan.auth.dto.converter.UserConverter;
import com.muyuan.auth.dto.converter.UserConverterImpl;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.UserInterface;
import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.api.dto.UserQueryRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
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

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private UserInterface userInterFace;

    private static UserConverter converter = new UserConverterImpl();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User();
    }

    /**
     * 登录
     *
     * @param username
     * @param platformType 平台类型
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username, PlatformType platformType) throws UsernameNotFoundException {
        Result<UserDTO> result = userInterFace.getUserByUsername(UserQueryRequest.builder()
                .platformType(platformType)
                .username(username)
                .build());

        if (!ResultUtil.isSuccess(result)) {
            throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
        }
        log.info("商家用户:{},登录", username);
        UserDTO userDTO = result.getData();
        Set<GrantedAuthority> authorities = new HashSet<>();

        if (ObjectUtils.isNotEmpty(userDTO.getRoles())) {
            userDTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }

        User user = converter.to(userDTO);
        user.setAuthorities(authorities);
        user.setPlatformType(platformType);
        return user;
    }

}
