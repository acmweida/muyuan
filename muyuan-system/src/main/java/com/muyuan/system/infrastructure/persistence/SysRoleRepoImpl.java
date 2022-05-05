package com.muyuan.system.infrastructure.persistence;

import com.muyuan.system.domain.model.SysRoleMenu;
import com.muyuan.system.infrastructure.persistence.dao.SysRoleMapper;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.repo.SysRoleRepo;
import com.muyuan.system.infrastructure.persistence.dao.SysRoleMenuMapper;
import lombok.AllArgsConstructor;
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
    public int insert(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public int batchInsert(List<SysRoleMenu> sysRoleMenus) {
        return sysRoleMenuMapper.batchInsert(sysRoleMenus,SysRoleMenuMapper.class);
    }

    @Override
    public int updateById(SysRole sysRole) {
        return sysRoleMapper.updateById(sysRole);
    }
}
