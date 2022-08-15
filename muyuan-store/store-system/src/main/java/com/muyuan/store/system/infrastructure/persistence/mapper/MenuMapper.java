package com.muyuan.store.system.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.store.system.infrastructure.config.mybatis.MemberBaseMapper;
import com.muyuan.store.system.domains.model.Menu;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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


     @Options(useGeneratedKeys = true, keyProperty = "id")
     @InsertProvider(value = CrudSqlProvider.class, method = "insert")
     Integer insertAuto(Menu dataObject);


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
    List<Menu>  selectMenuByRoleCodes(@Param("roleCodes") List<String> roleCodes);


    /**
     * 通过角色ID查询权限
     * @param roleIds
     * @return
     */
    List<Menu>  selectMenuByRoleId(@Param("roleIds") String...  roleIds);


    /**
     * 删除没有父节点的菜单
     * @return
     */
    int delete();


}
