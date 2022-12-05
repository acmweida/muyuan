package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
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
public interface RoleMapper extends UserBaseMapper<RoleDO> {



    List<RoleDO> selectRoleByUserId(Long userId,Integer type);

    List<RoleDO> selectRoleByMenuID(Long menuId);

    List<RoleDO> selectRoleByPermID(Long permID);

    Integer deleteRef(@Param("roleID") Long roleId, @Param("permissionIds") Long... permissionIds);

    Integer addRef(@Param("roleID") Long roleId, @Param("permissionIds") Long... permissionIds);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insertAuto(RoleDO roleDO);

}
