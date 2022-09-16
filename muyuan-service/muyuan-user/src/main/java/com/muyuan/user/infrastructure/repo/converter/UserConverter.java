package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.common.core.enums.UserType;
import com.muyuan.user.domain.model.entity.user.Role;
import com.muyuan.user.domain.model.entity.user.User;
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
@Mapper
public interface UserConverter {

    @Mappings({
            @Mapping(source = "id",target ="id.value"),
            @Mapping(source = "username",target ="username.value"),
            @Mapping(target = "type",expression = "java(UserTypeMap.map(userDO.getType()))")
    })
    User to(UserDO userDO);

    class UserTypeMap {
        static UserType map(Integer type) {
            return UserType.trance(type);
        }
    }

    List<Role> toRole(List<RoleDO> roleDOS);

}
