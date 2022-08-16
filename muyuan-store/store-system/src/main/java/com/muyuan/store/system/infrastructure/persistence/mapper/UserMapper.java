package com.muyuan.store.system.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.SystemBaseMapper;
import com.muyuan.store.system.domains.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends SystemBaseMapper<User> {

    User find(int userNo);

    List<User> selectAllocatedList(Map params);
}
