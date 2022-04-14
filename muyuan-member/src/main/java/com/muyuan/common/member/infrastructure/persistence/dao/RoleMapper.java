package com.muyuan.common.member.infrastructure.persistence.dao;

import com.muyuan.common.member.domain.model.Role;
import com.muyuan.common.member.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName RoleMapper
 * Description RoleMapper
 * @Author 2456910384
 * @Date 2021/12/24 11:21
 * @Version 1.0
 */
@Mapper
public interface RoleMapper extends MemberBaseMapper<Role> {

    List<Role> selectRoleByUserId(Long userId);
}
