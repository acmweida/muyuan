package com.store.system.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.store.system.domains.dto.RoleDTO;
import com.store.system.domains.model.Role;
import com.store.system.domains.model.RoleMenu;
import com.store.system.domains.repo.RoleRepo;
import com.store.system.infrastructure.persistence.mapper.RoleMapper;
import com.store.system.infrastructure.persistence.mapper.RoleMenuMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleRepoImpl
 * Description RoleRepoImpl
 * @Author 2456910384
 * @Date 2021/12/24 11:20
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class RoleRepoImpl implements RoleRepo {

    private RoleMapper roleMapper;

    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> selectRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<Role> select(RoleDTO roleDTO) {
        return select(roleDTO, null);
    }

    @Override
    public List<Role> select(RoleDTO roleDTO, Page page) {
        return roleMapper.selectList(new SqlBuilder(Role.class)
                .eq(RoleRepo.NAME, roleDTO.getName())
                .eq(RoleRepo.STATUS, roleDTO.getStatus())
                .page(page)
                .orderByAsc(RoleRepo.ORDER_NUM).build());
    }

    @Override
    public Role selectOne(Map params) {
        return roleMapper.selectOne(params);
    }

    @Override
    public void insert(Role sysRole) {
        roleMapper.insert(sysRole);
    }

    @Override
    public void batchInsert(List<RoleMenu> sysRoleMenus) {
        roleMenuMapper.batchInsert(sysRoleMenus);
    }

    @Override
    public void updateById(Role role) {
        roleMapper.updateBy(role, ID);
    }

    @Override
    public void deleteMenuByRoleId(Long roleId) {
        if (ObjectUtils.isEmpty(roleId)) {
            return;
        }
        roleMenuMapper.deleteBy(new SqlBuilder().eq(
                ROLE_ID, roleId
        ).build());
    }

    @Override
    public void deleteById(String... id) {
        roleMenuMapper.deleteBy(
                new SqlBuilder().in(ID, id)
                        .build()
        );
    }
}
