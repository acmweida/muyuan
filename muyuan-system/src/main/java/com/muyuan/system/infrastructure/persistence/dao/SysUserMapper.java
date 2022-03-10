package com.muyuan.system.infrastructure.persistence.dao;

import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends SystemBaseMapper<SysUser> {

    SysUser find(int userNo);
}
