package com.muyuan.store.system.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.SystemBaseMapper;
import com.muyuan.store.system.domains.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends SystemBaseMapper<UserRole> {

}
