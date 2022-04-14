package com.muyuan.common.member.domain.repo;

import com.muyuan.common.member.domain.model.Role;

import java.util.List;

public interface RoleRepo {

    List<Role> selectRoleByUserId(Long userId);
}
