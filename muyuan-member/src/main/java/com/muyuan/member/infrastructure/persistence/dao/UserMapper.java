package com.muyuan.member.infrastructure.persistence.dao;

import com.muyuan.member.domain.model.User;
import com.muyuan.member.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MemberBaseMapper<User> {

    User find(int userNo);
}
