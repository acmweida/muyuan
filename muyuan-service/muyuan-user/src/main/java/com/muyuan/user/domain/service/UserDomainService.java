package com.muyuan.user.domain.service;


import com.muyuan.user.api.dto.MerchantDTO;
import com.muyuan.user.domain.model.entity.user.Role;
import com.muyuan.user.domain.model.entity.user.User;
import com.muyuan.user.face.dto.RegisterDTO;
import com.muyuan.user.face.dto.UserVO;

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
    MerchantDTO getUserByUsername(String username);

    /**
     * 获取权限
     * @param roleCodes
     * @return
     */
    Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes);

    /**
     * 获取用户信息
     * @return
     */
    Optional<UserVO> getUserInfo();

    /**
     * 账户注册
     * @param registerInfo
     * @return
     */
    void add(RegisterDTO registerInfo);

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
     * @param shopId
     */
    void linkShop(Long shopId);

}
