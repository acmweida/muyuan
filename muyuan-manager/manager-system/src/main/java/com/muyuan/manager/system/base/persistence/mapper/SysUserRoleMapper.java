package com.muyuan.manager.system.base.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.SystemBaseMapper;
import com.muyuan.manager.system.model.SysUserRole;
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
