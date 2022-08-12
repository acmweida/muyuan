package com.muyuan.store.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.store.domains.dto.RoleDTO;
import com.muyuan.store.domains.model.Role;
import com.muyuan.store.domains.model.RoleMenu;

import java.util.List;
import java.util.Map;

public interface RoleRepo extends BaseRepo {

    String ROLE_ID = "roleId";

    List<Role> selectRoleByUserId(Long userId);

    List<Role> select(RoleDTO roleDTO);

    List<Role> select(RoleDTO roleDTO, Page page);

    Role selectOne(Map params);

    void insert(Role sysRole);

    void batchInsert(List<RoleMenu> sysRoleMenus);

    void updateById(Role sysRole);

    void deleteMenuByRoleId(Long roleId);

    void deleteById(String... id);
}
