package com.muyuan.system.domain.query;


import com.muyuan.system.domain.model.SysRole;

import java.util.List;

public interface SysRoleQuery {
    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUserId(Long userId);

}
