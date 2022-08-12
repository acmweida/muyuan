package com.muyuan.store.infrastructure.persistence.mapper;

import com.muyuan.store.domains.model.User;
import com.muyuan.store.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends MemberBaseMapper<User> {

    User find(int userNo);

    List<User> selectAllocatedList(Map params);
}
