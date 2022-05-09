package com.muyuan.member.api;

import com.muyuan.common.core.result.Result;
import com.muyuan.member.interfaces.dto.UserDTO;

import java.util.List;
import java.util.Set;


public interface UserInterface {

    /**
     *  通过账号获取用户信息
     * @param username 用户名
     * @return
     */
    Result<UserDTO> getUserByUsername(String username);

    /**
     * 通过角色获取权限集合
     * @param roleIds
     * @return
     */
    Set<String> getMenuPermissionByRoleCodes(List<String> roleIds);

}
