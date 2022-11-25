package com.muyuan.user.domain.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.MenuID;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.face.dto.RoleQueryCommand;

import java.util.List;

public interface RoleRepo {

    List<Role> selectRoleByUserId(UserID userId, PlatformType platformType);

    Page<Role> select(RoleQueryCommand command);

    Role select(Long id);

    Role selectRole(Role.Identify identify);

    /**
     * 通过菜单ID 查询关联角色
     *
     * @param menuId
     * @return
     */
    List<Role> selectByMenuID(MenuID menuId);

    /**
     * 通过权限ID 查询关联角色
     *
     * @param permId
     * @return
     */
    List<Role> selectByPermID(Long permId);

    boolean deleteRef(RoleID roleID, Long... permissionIds);

    boolean addRef(RoleID roleID, Long... permissionIds);

    boolean addRole(Role role);

    Role updateRole(Role role);

    /**
     * 删除
     * @param ids
     * @return old value
     */
    List<Role> deleteBy(Long... ids);

}
