package com.muyuan.system.application.query.impl;

import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.application.query.SysRoleQuery;
import com.muyuan.system.domain.repo.SysRoleRepo;
import org.apache.logging.log4j.core.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleQueryImpl
 * Description 角色信息查询
 * @Author 2456910384
 * @Date 2021/12/24 11:04
 * @Version 1.0
 */
@Service
public class SysRoleQueryImpl implements SysRoleQuery {

    @Autowired
    SysRoleRepo sysRoleRepo;

    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        Assert.isNonEmpty(userId);
        return sysRoleRepo.selectRoleByUserId(userId);
    }
}
