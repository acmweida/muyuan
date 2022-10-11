package com.muyuan.user.face.dto.mapper;

import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.api.dto.UserQueryRequest;
import com.muyuan.user.domain.model.entity.user.Role;
import com.muyuan.user.domain.model.entity.user.User;
import com.muyuan.user.face.dto.UserQueryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OperatorMapper
 * Description DTO 转换
 * @Author 2456910384
 * @Date 2022/9/14 9:09
 * @Version 1.0
 */
@Mapper
public interface UserMapper {

    UserQueryCommand toCommand(UserQueryRequest request);

    @Mappings({
            @Mapping(target = "id",source = "id.value"),
            @Mapping(target = "username",source = "username.value"),
            @Mapping(target = "roles",expression = "java(RoleMap.map(user.getRoles()))")
    })
    UserDTO toDto(User user);

    class RoleMap {
        static List<String> map(List<Role> value) {
            return value.stream().map(Role::getCode).collect(Collectors.toList());
        }
    }

}
