package com.muyuan.store.domains.model;

import lombok.Data;

/**
 * @ClassName RoleMenu
 * Description 角色菜单关联
 * @Author 2456910384
 * @Date 2022/1/27 16:39
 * @Version 1.0
 */
@Data
public class RoleMenu {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    public RoleMenu() {
    }

    public RoleMenu(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
