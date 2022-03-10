package com.muyuan.system.domain.query.impl;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.domain.entity.SysUserEntity;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.query.SysUserQuery;
import com.muyuan.system.domain.repo.SysUserRepo;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public int accountRegister(RegisterDTO registerInfo) {

        SysUser account = userRepo.selectOne(new SqlBuilder(SysUser.class).select("id")
                .eq("username", registerInfo.getUsername())
                .build());
        if (null != account) {
            return 1;
        }

        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        SysUser user = new SysUser();
        BeanUtils.copyProperties(registerInfo,user);
        user.setNickName(SysUserEntity.createUserName());
        user.setPassword(EncryptUtil.SHA1(registerInfo.getPassword() + salt, encryptKey));;
        user.setSalt(salt);
        user.setEncryptKey(encryptKey);

        userRepo.insert(user);

        return 0;
    }
}
