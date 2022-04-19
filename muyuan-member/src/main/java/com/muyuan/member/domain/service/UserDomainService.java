package com.muyuan.member.domain.service;

import com.muyuan.member.domain.model.User;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import com.muyuan.member.interfaces.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户域服务接口
 */
public interface UserDomainService {

    /**
     * 登录获取用户信息 内部RPC
     * @param username
     * @return
     */
    Optional<User> getUserByUsername(String username);


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    Optional<User> getByyId(Long  userId);

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int add(RegisterDTO registerInfo);
}
