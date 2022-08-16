package com.muyuan.store.system.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.SystemBaseMapper;
import com.muyuan.store.system.domains.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName SysRoleMenuMapper 接口
 * Description 角色菜单
 * @Author 2456910384
 * @Date 2022/4/29 9:58
 * @Version 1.0
 */
@Mapper
public interface RoleMenuMapper extends SystemBaseMapper<RoleMenu> {

    int delete();
}
