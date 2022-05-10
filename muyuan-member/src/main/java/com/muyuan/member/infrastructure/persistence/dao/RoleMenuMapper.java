package com.muyuan.member.infrastructure.persistence.dao;

import com.muyuan.member.domain.model.RoleMenu;
import com.muyuan.member.infrastructure.config.mybatis.MemberBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName SysRoleMenuMapper 接口
 * Description 角色菜单
 * @Author 2456910384
 * @Date 2022/4/29 9:58
 * @Version 1.0
 */
@Mapper
public interface RoleMenuMapper extends MemberBaseMapper<RoleMenu> {
}
