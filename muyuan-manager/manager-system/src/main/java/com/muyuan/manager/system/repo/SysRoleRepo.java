package com.muyuan.manager.system.repo;

import com.muyuan.manager.system.model.SysUserRole;

import java.util.List;

public interface SysRoleRepo {

    void batchInsertRole(List<SysUserRole> sysUserRoles);

}
