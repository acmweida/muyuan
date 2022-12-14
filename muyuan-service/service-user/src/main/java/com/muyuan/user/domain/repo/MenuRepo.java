package com.muyuan.user.domain.repo;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.valueobject.MenuID;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.face.dto.MenuQueryCommand;

import java.util.List;

public interface MenuRepo {

    /**
     * 角色菜单查询
     * @param roleCode
     * @param platformType
     * @return
     */
    List<Menu> selectByRoleCode(RoleCode roleCode, PlatformType platformType);

    /**
     * 权限菜单查询
     * @param permissions
     * @param platformType
     * @return
     */
    List<Menu> selectByPermissions(List<Permission> permissions, PlatformType platformType);

    List<Menu> list(MenuQueryCommand command);

    Menu selectMenu(MenuID id);


    Menu selectMenu(Menu.Identify identify);

    /**
     *
     * @param menu
     * @return old value
     */
    Menu updateDMenu(Menu menu);

    boolean addMenu(Menu menu);

    List<Menu> deleteBy(Long... ids);

    /**
     * 角色 菜单关联删除
     * @param menuIDS
     * @return
     */
    boolean deleteRef(MenuID... menuIDS);
}
