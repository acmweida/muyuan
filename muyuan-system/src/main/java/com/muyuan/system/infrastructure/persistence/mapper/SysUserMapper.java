package com.muyuan.system.infrastructure.persistence.mapper;

import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper extends SystemBaseMapper<SysUser> {

    SysUser find(int userNo);

    List<SysUser> selectAllocatedList(Map params);

    List<SysUser> selectUnallocatedList(Map params);
}
