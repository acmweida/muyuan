package com.muyuan.user.face.dto.mapper;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.face.dto.PermissionQueryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @ClassName OperatorMapper
 * Description DTO 转换
 * @Author 2456910384
 * @Date 2022/9/14 9:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface PermissionMapper {

    @Mappings(
            {
                    @Mapping(target = "platformType", expression = "java(PermissionMapper.map(request.getPlatformType()))"),
                    @Mapping(target = "userId.value", source = "userId")
            }
    )
    PermissionQueryCommand toCommand(PermissionQueryRequest request);

    // 默认用户类型为 会员类型
    static PlatformType map(PlatformType type) {
        return null == type ? PlatformType.MEMBER : type;
    }


}
