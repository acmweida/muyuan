package com.muyuan.system.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysRoleMenu;
import com.muyuan.system.domain.model.SysUserRole;
import com.muyuan.system.domain.repo.SysRoleRepo;
import com.muyuan.system.infrastructure.persistence.mapper.SysRoleMapper;
import com.muyuan.system.infrastructure.persistence.mapper.SysRoleMenuMapper;
import com.muyuan.system.infrastructure.persistence.mapper.SysUserRoleMapper;
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
public class SysRoleRepoImpl implements SysRoleRepo {

    private SysRoleMapper sysRoleMapper;

    private SysRoleMenuMapper sysRoleMenuMapper;

    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRole> selectRoleByUserId(Long userId) {
        return sysRoleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<SysRole> select(Map params) {
        return sysRoleMapper.selectList(params);
    }

    @Override
    public SysRole selectOne(Map params) {
        return sysRoleMapper.selectOne(params);
    }

    @Override
    public void insert(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void batchInsert(List<SysRoleMenu> sysRoleMenus) {
        sysRoleMenuMapper.batchInsert(sysRoleMenus);
    }

    @Override
    public void batchInsertRole(List<SysUserRole> sysUserRoles) {
        sysUserRoleMapper.batchInsert(sysUserRoles);
    }

    @Override
    public void updateById(SysRole sysRole) {
        sysRoleMapper.updateBy(sysRole,"id");
    }

    @Override
    public void deleteMenuByRoleId(Long  roleId) {
        if (ObjectUtils.isEmpty(roleId)) {
            return;
        }
        sysRoleMenuMapper.deleteBy(new SqlBuilder().eq(
                "roleId",roleId
        ).build());
    }

    @Override
    public void deleteById(String... id) {
        sysRoleMapper.deleteBy(
                new SqlBuilder().in("id",id)
                        .notEq("id",1)
                .build()
        );
    }

}
