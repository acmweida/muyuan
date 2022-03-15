package com.muyuan.system.domain.repo;

import com.muyuan.system.domain.model.SysRole;

import java.util.List;

public interface SysRoleRepo {

    List<SysRole> selectRoleByUserId(Long userId);
}
