package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.user.infrastructure.repo.dataobject.PermissionDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PermissionMapper 接口
 * Description 权限
 * @Author 2456910384
 * @Date 2022/10/12 17:14
 * @Version 1.0
 */
@Mapper
public interface PermissionMapper extends UserBaseMapper<PermissionDO> {

    String BUSINESS = "business";
    String MODULE = "module";
    String RESOURCE = "resource";
    String PERMS = "perms";

    List<PermissionDO> selectByRoleId(Long roleId);

    List<PermissionDO> selectByRoleCode(String roleCode);

    Integer deleteRef(@Param("permIds") Long... permIDs);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insertAuto(PermissionDO dataObject);

}
