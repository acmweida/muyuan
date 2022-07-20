package com.muyuan.member.infrastructure.persistence.mapper;

import com.muyuan.member.domains.model.UserRole;
import com.muyuan.member.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends MemberBaseMapper<UserRole> {

}
