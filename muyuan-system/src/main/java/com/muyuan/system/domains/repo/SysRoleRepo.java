package com.muyuan.system.domains.repo;

import com.muyuan.system.domains.model.SysRole;
import com.muyuan.system.domains.model.SysRoleMenu;
import com.muyuan.system.domains.model.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysRoleRepo {

    List<SysRole> selectRoleByUserId(Long userId);

    List<SysRole> select(Map params);

    SysRole selectOne(Map params);

    void insert(SysRole sysRole);

    void batchInsert(List<SysRoleMenu> sysRoleMenus);

    void batchInsertRole(List<SysUserRole> sysUserRoles);

    void updateById(SysRole sysRole);

    void deleteMenuByRoleId(Long roleId);

    void deleteById(String... id);

}
