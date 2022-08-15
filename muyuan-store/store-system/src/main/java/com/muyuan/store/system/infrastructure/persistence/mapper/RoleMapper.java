package com.muyuan.store.system.infrastructure.persistence.mapper;

import com.muyuan.store.system.infrastructure.config.mybatis.MemberBaseMapper;
import com.muyuan.store.system.domains.model.Role;
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

    String CODE = "code";


    List<Role> selectRoleByUserId(Long userId);
}
