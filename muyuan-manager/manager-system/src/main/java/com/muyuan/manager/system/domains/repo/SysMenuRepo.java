package com.muyuan.manager.system.domains.repo;

import com.muyuan.manager.system.domains.model.SysMenu;

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

    List<String>  selectMenuPermissionByRoleCodes(List<String> roleCodes);

    List<String>  selectMenuPermissionByRoleCode(String roleCode);

    List<SysMenu> select(Map params);

    List<SysMenu> listByRoleId(String... roleIds);

    SysMenu selectOne(Map params);

    void insert(SysMenu sysMenu);

    void deleteById(String... id);

    void updateById(SysMenu id);

    void refreshCache(String roleCode);

    void refreshCache();
}
