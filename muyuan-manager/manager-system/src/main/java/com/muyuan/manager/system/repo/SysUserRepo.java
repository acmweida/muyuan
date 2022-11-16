package com.muyuan.manager.system.repo;

import com.muyuan.manager.system.model.SysUser;
import com.muyuan.manager.system.model.SysUserRole;

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
