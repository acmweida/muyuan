package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.infrastructure.repo.dataobject.PermissionDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName OperatorConverter
 * Description DO转换
 * @Author 2456910384
 * @Date 2022/9/14 10:38
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface PermissionConverter {

    Permission to(PermissionDO permissionDO);

    List<Permission> to(List<PermissionDO> permissionDOS);

}
