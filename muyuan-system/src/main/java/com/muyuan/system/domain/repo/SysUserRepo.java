package com.muyuan.system.domain.repo;

import com.muyuan.system.domain.model.SysUser;

import java.util.Map;

public interface SysUserRepo {

    SysUser find(int userNo);

    SysUser selectOne(Map params);

    int insert(SysUser dataObject);
}
