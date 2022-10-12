package com.muyuan.user.domain.repo;

import com.muyuan.user.domain.model.entity.user.Permission;
import com.muyuan.user.domain.model.valueobject.RoleId;

import java.util.List;

public interface PermissionRepo {


    List<Permission> selectByRoles(RoleId roleId);

}
