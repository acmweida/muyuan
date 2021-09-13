package com.muyuan.member.infrastructure.persistence.dao;

import com.muyuan.common.repo.jdbc.mybatis.JdbcBaseMapper;
import com.muyuan.member.domain.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends JdbcBaseMapper<User> {

    User find(int userNo);
}
