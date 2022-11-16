package com.muyuan.user.domain.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.face.dto.PermissionQueryCommand;

import java.util.List;

public interface PermissionRepo {


    List<Permission> selectByRoles(RoleID roleId);

    Page<Permission> select(PermissionQueryCommand command);

}
