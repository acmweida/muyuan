package com.muyuan.member.application.query.impl;

import com.muyuan.member.domain.model.Role;
import com.muyuan.member.application.query.RoleQuery;
import com.muyuan.member.domain.repo.RoleRepo;
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
public class RoleQueryImpl implements RoleQuery {

    @Autowired
    RoleRepo roleRepo;

    @Override
    public List<Role> getRoleByUserId(Long userId) {
        Assert.isNonEmpty(userId);
        return roleRepo.selectRoleByUserId(userId);
    }
}
