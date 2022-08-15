package com.muyuan.manager.system.domains.repo;

import com.muyuan.manager.system.domains.model.SysUser;
import com.muyuan.manager.system.domains.model.SysUserRole;

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
