package com.muyuan.system.application.query;

import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.interfaces.dto.RegisterDTO;

import java.util.Optional;

public interface SysUserQuery {

    /**
     * 通过UserNO 获取用户信息
     * @param userId
     * @return
     */
    Optional<SysUser> getUserInfo(Long userId);

    /**
     * 通过Uaccount 获取用户信息
     * @param username
     * @return
     */
    Optional<SysUser> getUserByUsername(String username);

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int accountRegister(RegisterDTO registerInfo);
}
