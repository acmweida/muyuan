package com.muyuan.system.infrastructure.persistence.mapper;

import com.muyuan.system.domain.model.SysRoleMenu;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName SysRoleMenuMapper 接口
 * Description 角色菜单
 * @Author 2456910384
 * @Date 2022/4/29 9:58
 * @Version 1.0
 */
@Mapper
public interface SysRoleMenuMapper extends SystemBaseMapper<SysRoleMenu> {
}
