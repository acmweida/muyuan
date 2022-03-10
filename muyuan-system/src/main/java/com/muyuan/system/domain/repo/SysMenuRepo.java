package com.muyuan.system.domain.repo;

import com.muyuan.system.domain.model.SysMenu;

import java.util.List;

/**
 * @ClassName MenuRepo
 * Description 菜单权限 MenuRepo
 * @Author 2456910384
 * @Date 2022/2/11 16:28
 * @Version 1.0
 */
public interface SysMenuRepo {

    List<String>  selectMenuPermissionByRoleNames(List<String> roleNames);

    List<String>  selectMenuPermissionByRoleName(String roleName);

    List<SysMenu> selectMenuByRoleNames(List<String> roleNames);

    List<SysMenu> selectMenuByRoleName(String roleName);
}
