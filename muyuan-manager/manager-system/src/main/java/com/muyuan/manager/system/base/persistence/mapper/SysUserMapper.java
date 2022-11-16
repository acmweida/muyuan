package com.muyuan.manager.system.base.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.SystemBaseMapper;
import com.muyuan.manager.system.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper extends SystemBaseMapper<SysUser> {

    SysUser find(int userNo);

    List<SysUser> selectAllocatedList(Map params);

    List<SysUser> selectUnallocatedList(Map params);
}
