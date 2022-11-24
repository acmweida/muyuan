package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.entity.User;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.dataobject.UserDO;
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
public interface UserConverter {

    @Mappings({
            @Mapping(target = "id",source ="id.value")
    })
    RoleDO to(Role role);

    @Mappings({
            @Mapping(source = "id",target ="id.value"),
            @Mapping(source = "username",target ="username.value")
    })
    User to(UserDO userDO);

    @Mappings({
            @Mapping(target = "id",expression = "java(UserConverter.map(roleDO.getId()))"),
            @Mapping(target = "platformType",expression = "java(MenuConverter.map(roleDO.getPlatformType()))")
    })
    Role toRole(RoleDO roleDO);


    List<Role> toRole(List<RoleDO> roleDOS);

    static RoleID map(Long id) {
        return new RoleID(id);
    }



}
