package com.muyuan.system.infrastructure.persistence.mapper;

import com.muyuan.system.domains.model.SysUserRole;
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
