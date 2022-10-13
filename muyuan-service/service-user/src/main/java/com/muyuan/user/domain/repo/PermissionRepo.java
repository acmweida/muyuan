package com.muyuan.user.domain.repo;

import com.muyuan.user.domain.model.entity.user.Permission;
import com.muyuan.user.domain.model.valueobject.RoleID;

import java.util.List;

public interface PermissionRepo {


    List<Permission> selectByRoles(RoleID roleId);

}
