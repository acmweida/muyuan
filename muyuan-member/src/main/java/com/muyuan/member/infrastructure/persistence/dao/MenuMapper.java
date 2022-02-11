package com.muyuan.member.infrastructure.persistence.dao;

import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.model.Role;
import com.muyuan.member.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RoleMapper
 * Description RoleMapper
 * @Author 2456910384
 * @Date 2021/12/24 11:21
 * @Version 1.0
 */
@Mapper
public interface MenuMapper extends MemberBaseMapper<Menu> {

    List<String>  selectMenuPermissionByRoleNames(@Param("roleNames") List<String> roleNames);
}
