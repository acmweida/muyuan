package com.store.system.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.store.system.domains.dto.MenuDTO;
import com.store.system.domains.model.Menu;

import java.util.List;

/**
 * @ClassName MenuRepo
 * Description 菜单权限 MenuRepo
 * @Author 2456910384
 * @Date 2022/2/11 16:28
 * @Version 1.0
 */
public interface MenuRepo extends BaseRepo {

    String STATUS_OK = "0";

    List<String>  selectMenuPermissionByRoleCodes(List<String> roleNames);

    List<String>  selectMenuPermissionByRoleCode(String roleName);

    List<Menu> selectMenuByRoleCodes(List<String> roleCodes);

    List<Menu> selectMenuByRoleCode(String roleName);

    Menu selectOne(Menu menu);

    void insert(Menu sysMenu);

    List<Menu> select(MenuDTO menuDTO);

    List<Menu> listByRoleId(String... roleIds);

    void deleteById(String... id);

    void updateById(Menu id);

    void refreshCache(String roleCode);

    void refreshCache();
}
