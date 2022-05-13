package com.muyuan.member.domain.service;

import com.muyuan.member.domain.model.User;
import com.muyuan.member.interfaces.dto.RegisterDTO;

import java.util.Optional;

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

    /**
     * 检查唯一性
     * @param sysUser
     * @return
     */
    String checkAccountNameUnique(User sysUser);
}
