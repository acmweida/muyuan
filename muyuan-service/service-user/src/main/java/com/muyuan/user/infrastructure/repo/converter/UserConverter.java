package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.user.domain.model.entity.user.Role;
import com.muyuan.user.domain.model.entity.user.User;
import com.muyuan.user.domain.model.valueobject.RoleId;
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
            @Mapping(source = "username",target ="username.value")
    })
    User to(UserDO userDO);


    @Mappings({
            @Mapping(target ="id",expression = "java(RoleIdMap.map(roleDO,id))")
    })
    List<Role> toRole(List<RoleDO> roleDOS);

    class RoleIdMap {
        static RoleId map(Long id) {
            return new RoleId(id);
        }
    }

}
