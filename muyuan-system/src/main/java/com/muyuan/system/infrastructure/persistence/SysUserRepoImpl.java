package com.muyuan.system.infrastructure.persistence;

import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.repo.SysUserRepo;
import com.muyuan.system.infrastructure.persistence.dao.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SysUserRepoImpl implements SysUserRepo {

    @Autowired
    SysUserMapper userMapper;

    @Override
    public SysUser find(int userNo) {
        return userMapper.find(userNo);
    }

    @Override
    public SysUser selectOne(Map params) {
        return userMapper.selectOne(params);
    }

    @Override
    public boolean insert(SysUser dataObject) {
         return userMapper.insert(dataObject) > 0;
    }
}
