package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.domain.entity.SysUserEntity;
import com.muyuan.system.domain.factories.SysUserFactory;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.query.SysUserQuery;
import com.muyuan.system.domain.repo.SysUserRepo;
import com.muyuan.system.domain.service.SysUserDomainService;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class SysUserDomainServiceImpl implements SysUserDomainService {

    private SysUserQuery sysUserQuery;

    private SysUserRepo sysUserRepo;

    @Override
    public Optional<SysUser> getByyUsername(String username) {
        final Optional<SysUser> userInfo = sysUserQuery.get(new SysUser(username));
        if (!userInfo.isPresent()) {
            return Optional.empty();
        }
        return userInfo;
    }

    @Override
    public Optional<SysUser> getByyId(Long userId) {
        final Optional<SysUser> userInfo = sysUserQuery.get(new SysUser(userId));
        if (!userInfo.isPresent()) {
            return Optional.empty();
        }
        return userInfo;
    }

    @Override
    @Transactional
    public int add(RegisterDTO registerInfo) {
        SysUser sysUser = SysUserFactory.newSysUser(registerInfo);
        return sysUserRepo.insert(sysUser);
    }

    @Override
    public String checkAccountNameUnique(SysUser sysUser) {
        Long id = null == sysUser.getId() ? 0 : sysUser.getId();
        SysUser account = sysUserRepo.selectOne(new SqlBuilder(SysUser.class).select("id")
                .eq("username", sysUser.getUsername())
                .build());
        if (null != account && !id.equals(account.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

}
