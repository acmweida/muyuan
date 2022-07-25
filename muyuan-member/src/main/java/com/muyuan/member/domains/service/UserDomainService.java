package com.muyuan.member.domains.service;

import com.muyuan.member.domains.model.Role;
import com.muyuan.member.domains.model.User;

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
     * @param user
     * @return
     */
    void add(User user);

    /**
     * 用户添加角色
     * @param user
     * @param role
     */
    void addRole(User user, Role role);

    /**
     * 检查唯一性
     * @param user
     * @return
     */
    String checkAccountNameUnique(User user);

    /**
     * 设置店铺ID
     * @param userId
     * @param shopId
     */
    void linkShop(Long userId,Long shopId);

}
