package com.muyuan.user.domain.service;

import java.util.Optional;

/**
 * @ClassName UserService
 * Description
 * @Author 2456910384
 * @Date 2022/12/12 15:44
 * @Version 1.0
 */
public interface UserService<T> {

    /**
     * 通过用户名称 获取用户信息
     * 默认用户类型 MEMBER
     * @param username
     * @return
     */
    Optional<T> getUserByUsername(String username);
}
