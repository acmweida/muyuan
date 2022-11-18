package com.muyuan.user.domain.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.face.dto.PermissionQueryCommand;

import java.util.List;

public interface PermissionRepo {

    List<Permission> selectByRoles(RoleID roleId);

    Page<Permission> select(PermissionQueryCommand command);

    Permission selectPermission(Long id);

    Permission selectPermission(Permission.Identify permission);

    boolean addPermission(Permission menu);

    /**
     * 更新信息
     * @param permission
     * @return old value
     */
    Permission updatePermission(Permission permission);

    /**
     * 删除
     * @param ids
     * @return old value
     */
    List<Permission> deleteBy(Long... ids);

    /**
     * 角色 权限关联删除
     * @param permIds
     * @return
     */
    boolean deleteRef(Long... permIds);
}
