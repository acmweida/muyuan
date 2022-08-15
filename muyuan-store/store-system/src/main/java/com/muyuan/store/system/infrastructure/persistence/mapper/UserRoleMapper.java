package com.muyuan.store.system.infrastructure.persistence.mapper;

import com.muyuan.store.system.domains.model.UserRole;
import com.muyuan.store.system.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends MemberBaseMapper<UserRole> {

}
