package com.muyuan.user.domain.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.face.dto.RoleQueryCommand;

import java.util.List;

public interface RoleRepo {

    List<Role> selectRoleByUserId(UserID userId, PlatformType platformType);

//    List<Role> select(RoleDTO roleDTO);
//
    Page<Role> select(RoleQueryCommand command);

    Role select(Long id);
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
