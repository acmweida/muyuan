package com.muyuan.system.infrastructure.persistence.dao;

import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RoleMapper
 * Description SysRoleMapper
 * @Author 2456910384
 * @Date 2021/12/24 11:21
 * @Version 1.0
 */
@Mapper
public interface SysMenuMapper extends SystemBaseMapper<SysMenu> {

    /**
     * 通过角色名称查询权限
     * @param roleCodes
     * @return
     */
    List<String>  selectMenuPermissionByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    /**
     * 通过角色查询菜单权限
     * @param roleCodes
     * @return
     */
    List<SysMenu>  selectMenuByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    /**
     * 通过角色ID查询权限
     * @param roleIds
     * @return
     */
    List<SysMenu>  selectMenuByRoleId(@Param("roleIds") String...  roleIds);


}
