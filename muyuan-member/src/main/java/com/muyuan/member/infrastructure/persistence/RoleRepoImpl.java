package com.muyuan.member.infrastructure.persistence;

import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.repo.RoleRepo;
import com.muyuan.member.infrastructure.persistence.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleRepoImpl implements RoleRepo {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> selectRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }
}
