package com.muyuan.manager.auth.service.impl;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.manager.auth.dto.UserInfo;
import com.muyuan.user.api.OperatorInterface;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;
import lombok.extern.slf4j.Slf4j;
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

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    OperatorInterface operatorInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<OperatorDTO> result = operatorInterface.getOperatorByUsername(OperatorQueryRequest.builder()
                .username(username)
                .build());
        if (!ResultUtil.isSuccess(result)) {
            throw new UsernameNotFoundException(ResponseCode.USER_ONT_FOUND.getMsg());
        }

        log.info("管理用户:{},登录", username);
        OperatorDTO userDTO = result.getData();
        Set<GrantedAuthority> authorities = new HashSet<>();

        userDTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDTO, userInfo);
        userInfo.setAuthorities(authorities);
        return userInfo;
    }

}
