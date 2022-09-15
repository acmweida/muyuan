package com.muyuan.user.domain.repo;

import com.muyuan.user.domain.model.entity.user.Role;

import java.util.List;

public interface RoleRepo {

    String ROLE_ID = "roleId";

    List<Role> selectRoleByUserId(Long userId);

//    List<Role> select(RoleDTO roleDTO);
//
//    List<Role> select(RoleDTO roleDTO, Page page);
//
//    Role selectOne(Map params);
//
//    void insert(Role sysRole);
//
//    void batchInsert(List<RoleMenu> sysRoleMenus);
//
//    void updateById(Role sysRole);
//
//    void deleteMenuByRoleId(Long roleId);
//
//    void deleteById(String... id);
}
