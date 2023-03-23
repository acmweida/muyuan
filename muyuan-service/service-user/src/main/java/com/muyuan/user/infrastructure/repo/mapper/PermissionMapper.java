package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.user.infrastructure.repo.dataobject.PermissionDO;
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

    List<PermissionDO> selectByRoleId(Long roleId);

    List<PermissionDO> selectByRoleCode(String roleCode);

    Integer deleteRef(@Param("permIds") Long... permIDs);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    Integer insertAuto(PermissionDO dataObject);

}
