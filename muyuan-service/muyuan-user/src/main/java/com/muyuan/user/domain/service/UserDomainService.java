package com.muyuan.user.domain.service;


import com.muyuan.user.domain.model.entity.user.User;
import com.muyuan.user.face.dto.UserQueryCommand;

import java.util.Optional;

/**
 * 用户域服务接口
 */
public interface UserDomainService {

//    /**
//     * 获取权限
//     * @param roleCodes
//     * @return
//     */
//    Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes);
//
//    /**
//     * 获取用户信息
//     * @return
//     */
//    Optional<UserVO> getUserInfo();
//
//    /**
//     * 账户注册
//     * @param registerInfo
//     * @return
//     */
//    void add(RegisterDTO registerInfo);

    /**
     * 登录获取用户信息 内部RPC
     * @param command
     * @return
     */
    Optional<User> getUserByUsername(UserQueryCommand command);


//    /**
//     * 获取用户信息
//     * @param userId
//     * @return
//     */
//    Optional<Operator> getByyId(Long  userId);
//
//    /**
//     * 账户注册
//     * @param operator
//     * @return
//     */
//    void add(Operator operator);
//
//    /**
//     * 用户添加角色
//     * @param operator
//     * @param role
//     */
//    void addRole(Operator operator, Role role);
//
//    /**
//     * 检查唯一性
//     * @param operator
//     * @return
//     */
//    String checkAccountNameUnique(Operator operator);
//
//    /**
//     * 设置店铺ID
//     * @param shopId
//     */
//    void linkShop(Long shopId);

}
