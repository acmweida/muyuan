package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.user.infrastructure.repo.dataobject.PermissionDO;
import org.apache.ibatis.annotations.Mapper;

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

    List<PermissionDO> selectByRoleId(Long roleId);
}
