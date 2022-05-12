package com.muyuan.member.application.service;

import com.muyuan.member.domain.vo.UserVO;
import com.muyuan.member.interfaces.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserApplicationService {

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
    Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes);

    /**
     * 获取用户信息
     * @return
     */
    Optional<UserVO> getUserInfo();
}
