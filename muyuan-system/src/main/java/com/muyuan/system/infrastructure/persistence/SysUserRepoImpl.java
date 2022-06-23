package com.muyuan.system.infrastructure.persistence;

import com.muyuan.system.domains.model.SysUser;
import com.muyuan.system.domains.model.SysUserRole;
import com.muyuan.system.domains.repo.SysUserRepo;
import com.muyuan.system.infrastructure.persistence.mapper.SysUserMapper;
import com.muyuan.system.infrastructure.persistence.mapper.SysUserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class SysUserRepoImpl implements SysUserRepo {

    private SysUserMapper userMapper;

    private SysUserRoleMapper sysUserRoleMapper;


    @Override
    public SysUser find(int userNo) {
        return userMapper.find(userNo);
    }

    @Override
    public SysUser selectOne(Map params) {
        return userMapper.selectOne(params);
    }

    @Override
    public void insert(SysUser dataObject) {
         userMapper.insert(dataObject);
    }

    @Override
    public List<SysUser> select(Map params) {
        return userMapper.selectList(params);
    }

    @Override
    public void batchInsert(List<SysUserRole> list) {
        sysUserRoleMapper.batchInsert(list);
    }

    @Override
    public List<SysUser> selectAllocatedList(Map params) {
        return userMapper.selectAllocatedList(params);
    }

    @Override
    public List<SysUser> selectUnallocatedList(Map params) {
        return userMapper.selectUnallocatedList(params);
    }
}
