package com.muyuan.user.face.dto.mapper;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.face.dto.PermissionQueryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @ClassName OperatorMapper
 * Description DTO 转换
 * @Author 2456910384
 * @Date 2022/9/14 9:09
 * @Version 1.0
 */
@Mapper
public interface PermissionMapper {

    @Mapping(target = "platformType",expression = "java(PlatformTypeMap.map(request.getPlatformType()))")
    PermissionQueryCommand toCommand(PermissionQueryRequest request);

//    @Mappings({
//            @Mapping(target = "id",source = "id.value"),
//            @Mapping(target = "username",source = "username.value"),
//            @Mapping(target = "roles",expression = "java(RoleMap.map(user.getRoles()))")
//    })
//    UserDTO toDto(User user);
//
//    class RoleMap {
//        static List<String> map(List<Role> value) {
//            return value.stream().map(Role::getCode).collect(Collectors.toList());
//        }
//    }

    class PlatformTypeMap {
        // 默认用户类型为 会员类型
        static PlatformType map(PlatformType type) {
            return  null == type ?  PlatformType.MEMBER : type;
        }
    }

}
