package com.muyuan.user.face.dto.mapper;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.api.dto.PermissionRequest;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.face.dto.PermissionCommand;
import com.muyuan.user.face.dto.PermissionQueryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

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
                    @Mapping(target = "platformType", expression = "java(PermissionMapper.map(request.getPlatformType()))")
            }
    )

    PermissionQueryCommand toCommand(PermissionQueryRequest request);

    PermissionCommand toCommand(PermissionRequest request);

    // 默认用户类型为 会员类型
    static PlatformType map(PlatformType type) {
        return null == type ? PlatformType.MEMBER : type;
    }


    List<PermissionDTO> toDTO(List<Permission> permissions);

    PermissionDTO toDTO(Permission permissions);


}
