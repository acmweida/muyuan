package com.muyuan.member.domains.repo;

import com.muyuan.member.domains.model.Role;
import com.muyuan.member.domains.model.RoleMenu;

import java.util.List;
import java.util.Map;

public interface RoleRepo {

    List<Role> selectRoleByUserId(Long userId);

    List<Role> select(Map params);

    Role selectOne(Map params);

    void insert(Role sysRole);

    void batchInsert(List<RoleMenu> sysRoleMenus);

    void updateById(Role sysRole);

    void deleteMenuByRoleId(Long roleId);

    void deleteById(String... id);
}
