package com.store.system.infrastructure.persistence.mapper;

import com.store.system.domains.model.UserRole;
import com.store.system.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends MemberBaseMapper<UserRole> {

}
