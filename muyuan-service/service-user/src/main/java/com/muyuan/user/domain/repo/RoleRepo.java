package com.muyuan.user.domain.repo;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.user.Role;
import com.muyuan.user.domain.model.valueobject.UserID;

import java.util.List;

public interface RoleRepo {

    List<Role> selectRoleByUserId(UserID userId, PlatformType platformType);

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
