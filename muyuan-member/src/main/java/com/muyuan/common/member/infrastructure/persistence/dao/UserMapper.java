package com.muyuan.common.member.infrastructure.persistence.dao;

import com.muyuan.common.member.domain.model.User;
import com.muyuan.common.member.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MemberBaseMapper<User> {

    User find(int userNo);
}
