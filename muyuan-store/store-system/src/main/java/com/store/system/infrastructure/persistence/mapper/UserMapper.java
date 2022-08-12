package com.store.system.infrastructure.persistence.mapper;

import com.store.system.domains.model.User;
import com.store.system.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends MemberBaseMapper<User> {

    User find(int userNo);

    List<User> selectAllocatedList(Map params);
}
