package com.muyuan.system.api;

import com.muyuan.common.core.result.Result;
import com.muyuan.system.interfaces.to.SysUserTO;

import java.util.List;
import java.util.Set;

/**
 * @ClassName SysUserInterface 接口
 * Description RPC 接口 系统用户
 * @Author 2456910384
 * @Date 2022/3/10 11:11
 * @Version 1.0
 */
public interface SysUserInterface {

    /**
     *  通过账号获取系统用户信息
     * @param username 用户名
     * @return
     */
    Result<SysUserTO> getUserByUsername(String username);

    /**
     * 通过角色获取权限集合
     * @param roleCodes
     * @return
     */
    Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes);
}
