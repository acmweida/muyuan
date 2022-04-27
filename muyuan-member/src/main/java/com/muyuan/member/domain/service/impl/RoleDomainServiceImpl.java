package com.muyuan.member.domain.service.impl;

import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.query.RoleQuery;
import com.muyuan.member.domain.repo.RoleRepo;
import com.muyuan.member.domain.service.RoleDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName RoleQueryImpl
 * Description 角色域服务
 * @Author 2456910384
 * @Date 2021/12/24 11:04
 * @Version 1.0
 */
@AllArgsConstructor
@Service
@Slf4j
public class RoleDomainServiceImpl implements RoleDomainService {

    private RoleRepo roleRepo;;

    private RoleQuery roleQuery;

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> getRoleByUserId(Long userId) {
        Assert.notNull(userId,"user id is null");
        return roleQuery.getRoleByUserId(userId);
    }

}
