package com.muyuan.member.domain.query;

import com.muyuan.member.domain.model.User;
import com.muyuan.member.interfaces.dto.RegisterDTO;

import java.util.Optional;

public interface UserQuery {

    /**
     * 通过UserNO 获取用户信息
     * @param userId
     * @return
     */
    Optional<User> getUserInfo(Long userId);

    /**
     * 通过Uaccount 获取用户信息
     * @param username
     * @return
     */
    Optional<User> getUserByUsername(String username);

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int accountRegister(RegisterDTO registerInfo);
}
