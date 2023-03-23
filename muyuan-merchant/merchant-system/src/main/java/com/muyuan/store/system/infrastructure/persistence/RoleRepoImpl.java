package com.muyuan.store.system.infrastructure.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
import com.muyuan.store.system.domains.dto.RoleDTO;
import com.muyuan.store.system.domains.model.Role;
import com.muyuan.store.system.domains.model.RoleMenu;
import com.muyuan.store.system.domains.repo.RoleRepo;
import com.muyuan.store.system.infrastructure.persistence.mapper.RoleMapper;
import com.muyuan.store.system.infrastructure.persistence.mapper.RoleMenuMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.assertj.core.util.Arrays;
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
        return roleMapper.selectList(new LambdaQueryWrapper<Role>()
                .eq(Role::getName, roleDTO.getName())
                .eq(Role::getStatus, roleDTO.getStatus())
//                .page(page)
                .orderByAsc(Role::getOrderNum));
    }

    @Override
    public Role selectOne(Map params) {
        return null;
//        return roleMapper.selectOne(params);
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
        roleMapper.updateById(role);
    }

    @Override
    public void deleteMenuByRoleId(Long roleId) {
        if (ObjectUtils.isEmpty(roleId)) {
            return;
        }
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>().eq(
                RoleMenu::getRoleId, roleId
        ));
    }

    @Override
    public void deleteById(String... id) {
        roleMenuMapper.deleteBatchIds(Arrays.asList(id));
    }
}
