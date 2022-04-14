package com.muyuan.member.application.service;

import com.muyuan.member.interfaces.dto.UserDTO;

import java.util.List;
import java.util.Set;

public interface UserService {

    /**
     * 登录获取用户信息 内部RPC
     * @param username
     * @return
     */
    UserDTO getUserByUsername(String username);

    /**
     * 获取权限
     * @param roleIds
     * @return
     */
    Set<String> getMenuPermissionByRoleNames(List<String> roleIds);
}
