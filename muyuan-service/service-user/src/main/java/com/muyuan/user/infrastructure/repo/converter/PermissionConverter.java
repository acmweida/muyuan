package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.user.Permission;
import com.muyuan.user.infrastructure.repo.dataobject.PermissionDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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

    @Mappings({
            @Mapping(target = "type", expression = "java(PermissionConverter.map(permissionDO.getType()))")
    })
    Permission to(PermissionDO permissionDO);

    static PlatformType map(Integer type) {
        return PlatformType.trance(type);
    }

    List<Permission> to(List<PermissionDO> permissionDOS);

}
