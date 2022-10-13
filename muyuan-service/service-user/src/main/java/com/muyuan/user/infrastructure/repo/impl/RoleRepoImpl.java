package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.user.Role;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.repo.RoleRepo;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName RoleRepoImpl
 * Description Role
 * @Author 2456910384
 * @Date 2022/10/12 15:13
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class RoleRepoImpl implements RoleRepo {

    private RoleMapper roleMapper;

    private UserConverter converter;

    @Override
    public List<Role> selectRoleByUserId(UserID userId, PlatformType platformType) {
        List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(userId.getValue(), platformType.getCode());
        return converter.toRole(roleDOS);
    }
}
