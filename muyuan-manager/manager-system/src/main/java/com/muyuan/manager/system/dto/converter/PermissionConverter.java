package com.muyuan.manager.system.dto.converter;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.manager.system.dto.PermissionParams;
import com.muyuan.manager.system.dto.vo.PermissionVO;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.PermissionRequest;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @ClassName MenuConverter
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 17:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface PermissionConverter {


    List<PermissionVO> toVO(List<PermissionDTO> permissionVOS);

    @Mappings({
            @Mapping(target = "platformType",expression = "java(PermissionConverter.map(params.getPlatformType()))")
    })
    PermissionRequest to(PermissionParams params);

    static PlatformType map(String type) {
        return StringUtils.isEmpty(type) ? PlatformType.OPERATOR : PlatformType.trance(type);
    }

    @Mappings({
            @Mapping(target = "platformType",source = "platformType.code")
    })
    PermissionVO toVO(PermissionDTO permissionDTO);

}
