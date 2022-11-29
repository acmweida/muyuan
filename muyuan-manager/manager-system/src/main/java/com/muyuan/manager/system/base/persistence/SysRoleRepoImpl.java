package com.muyuan.manager.system.base.persistence;

import com.muyuan.manager.system.base.persistence.mapper.SysUserRoleMapper;
import com.muyuan.manager.system.model.SysUserRole;
import com.muyuan.manager.system.repo.SysRoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public void batchInsertRole(List<SysUserRole> sysUserRoles) {
        sysUserRoleMapper.batchInsert(sysUserRoles);
    }

}
