package com.muyuan.system.domain.repo;

import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.model.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysUserRepo {

    SysUser find(int userNo);

    SysUser selectOne(Map params);

    void insert(SysUser dataObject);

    List<SysUser> select(Map params);

    void batchInsert(List<SysUserRole> list);

    List<SysUser> selectAllocatedList(Map params);

    List<SysUser> selectUnallocatedList(Map params);
}
