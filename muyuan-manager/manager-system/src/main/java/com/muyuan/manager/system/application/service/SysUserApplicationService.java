package com.muyuan.manager.system.application.service;

import com.muyuan.manager.system.domains.vo.SysUserVO;
import com.muyuan.user.api.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户应用接口
 */
public interface SysUserApplicationService {

    /**
     * 登录获取用户信息 内部RPC
     * @param username
     * @return
     */
    UserDTO getUserByUsername(String username);


    /**
     * 获取用户信息
     * @return
     */
    Optional<SysUserVO> get(Long id);


    /**
     * 获取权限
     * @param roleIds
     * @return
     */
    Set<String> getMenuPermissionByRoleCodes(List<String> roleIds);

}
