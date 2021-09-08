package com.muyuan.auth.repo.dao;

import com.muyuan.auth.model.User;
import com.muyuan.common.repo.jdbc.mybatis.JdbcBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends JdbcBaseMapper<User> {

    User find(int userNo);
}
