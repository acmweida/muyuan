package com.muyuan.member.domain.repo;

import com.muyuan.member.domain.model.Menu;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MenuRepo
 * Description 菜单权限 MenuRepo
 * @Author 2456910384
 * @Date 2022/2/11 16:28
 * @Version 1.0
 */
public interface MenuRepo {

    List<String>  selectMenuPermissionByRoleCodes(List<String> roleNames);

    List<String>  selectMenuPermissionByRoleName(String roleName);

    List<Menu> selectMenuByRoleNames(List<String> roleNames);

    List<Menu> selectMenuByRoleName(String roleName);

    List<Menu> selectMenuByRoleCodes(List<String> roleCodes);

    Menu selectOne(Map params);

    void insert(Menu sysMenu);

    List<Menu> select(Map params);

    List<Menu> listByRoleId(String... roleIds);

    void deleteById(String... id);

    void updateById(Menu id);

    void refreshCache(String roleCode);

    void refreshCache();
}
