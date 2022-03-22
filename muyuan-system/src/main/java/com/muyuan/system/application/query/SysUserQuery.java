package com.muyuan.system.application.query;

import com.muyuan.system.domain.model.SysUser;

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

}
