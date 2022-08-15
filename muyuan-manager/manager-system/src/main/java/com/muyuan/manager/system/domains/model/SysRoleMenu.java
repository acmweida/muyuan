package com.muyuan.manager.system.domains.model;

import lombok.Data;

/**
 * @ClassName SysRoleMenu
 * Description 系统角色菜单
 * @Author 2456910384
 * @Date 2022/4/29 9:50
 * @Version 1.0
 */
@Data
public class SysRoleMenu {

    private Long roleId;

    private Long menuId;

    public SysRoleMenu() {
    }

    public SysRoleMenu(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
