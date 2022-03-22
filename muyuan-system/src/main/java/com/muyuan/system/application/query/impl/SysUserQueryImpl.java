package com.muyuan.system.application.query.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.application.query.SysUserQuery;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.repo.SysUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SysUserQueryImpl implements SysUserQuery {

    @Autowired
    SysUserRepo userRepo;

    @Override
    public Optional<SysUser> getUserInfo(Long userId) {
        final SysUser user = userRepo.selectOne(new SqlBuilder(SysUser.class)
                .eq("id", userId)
                .eq("status",0)
                .build());
        if (null == user) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Override
    public Optional<SysUser> getUserByUsername(String username) {
        final SysUser user = userRepo.selectOne(new SqlBuilder(SysUser.class)
                .eq("username", username)
                .eq("status",0)
                .build());
        if (null == user) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
