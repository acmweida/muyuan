package com.muyuan.system.domain.repo;

import com.muyuan.system.domain.model.SysMenu;

import java.util.List;
import java.util.Map;

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

    List<SysMenu> select(Map params);

    SysMenu selectOne(Map params);

    int insert(SysMenu sysMenu);

    int deleteById(String... id);

    int updateById(SysMenu id);
}
