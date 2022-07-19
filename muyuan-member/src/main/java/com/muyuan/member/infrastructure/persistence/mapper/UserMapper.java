package com.muyuan.member.infrastructure.persistence.mapper;

import com.muyuan.member.domains.model.User;
import com.muyuan.member.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends MemberBaseMapper<User> {

    String PHONE = "phone";

    User find(int userNo);

    List<User> selectAllocatedList(Map params);
}
