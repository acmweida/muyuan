package com.muyuan.member.repo.dao;

import com.muyuan.common.repo.jdbc.mybatis.JdbcBaseMapper;
import com.muyuan.member.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends JdbcBaseMapper<User> {
}
