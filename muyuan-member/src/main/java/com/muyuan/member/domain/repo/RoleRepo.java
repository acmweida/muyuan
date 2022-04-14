package com.muyuan.member.domain.repo;

import com.muyuan.member.domain.model.Role;

import java.util.List;

public interface RoleRepo {

    List<Role> selectRoleByUserId(Long userId);
}
