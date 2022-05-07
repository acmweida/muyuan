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

    List<String>  selectMenuPermissionByRoleCodes(List<String> roleCodes);

    List<String>  selectMenuPermissionByRoleCode(String roleCode);

    List<SysMenu> selectMenuByRoleCodes(List<String> roleCodes);

    List<SysMenu> selectMenuByRoleCode(String roleCode);

    List<SysMenu> select(Map params);

    List<SysMenu> listByRoleId(String... roleIds);

    SysMenu selectOne(Map params);

    int insert(SysMenu sysMenu);

    int deleteById(String... id);

    int updateById(SysMenu id);

    void refreshCache(String roleCode);

    void refreshCache();
}
