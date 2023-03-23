package com.muyuan.store.system.domains.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.common.BaseRepo;
import com.muyuan.store.system.domains.dto.RoleDTO;
import com.muyuan.store.system.domains.model.Role;
import com.muyuan.store.system.domains.model.RoleMenu;

import java.util.List;

public interface RoleRepo extends BaseRepo {

    String ROLE_ID = "roleId";

    List<Role> selectRoleByUserId(Long userId);

    List<Role> select(RoleDTO roleDTO);

    List<Role> select(RoleDTO roleDTO, Page page);

    Role selectOne(Role params);

    void insert(Role sysRole);

    void batchInsert(List<RoleMenu> sysRoleMenus);

    void updateById(Role sysRole);

    void deleteMenuByRoleId(Long roleId);

    void deleteById(String... id);
}
