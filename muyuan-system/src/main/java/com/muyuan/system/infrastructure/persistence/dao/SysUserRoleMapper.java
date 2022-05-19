package com.muyuan.system.infrastructure.persistence.dao;

import com.muyuan.system.domain.model.SysRoleMenu;
import com.muyuan.system.domain.model.SysUserRole;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName SysUserRoleMapper 接口
 * Description 用户角色关联
 * @Author 2456910384
 * @Date 2022/5/19 16:08
 * @Version 1.0
 */
@Mapper
public interface SysUserRoleMapper extends SystemBaseMapper<SysUserRole> {
}
