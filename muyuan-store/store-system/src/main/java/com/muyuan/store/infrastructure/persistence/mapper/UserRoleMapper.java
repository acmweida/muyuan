package com.muyuan.store.infrastructure.persistence.mapper;

import com.muyuan.store.domains.model.UserRole;
import com.muyuan.store.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends MemberBaseMapper<UserRole> {

}
