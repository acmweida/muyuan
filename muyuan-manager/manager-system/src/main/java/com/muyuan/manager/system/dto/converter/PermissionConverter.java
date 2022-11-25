package com.muyuan.manager.system.dto.converter;

import com.muyuan.manager.system.dto.PermissionParams;
import com.muyuan.manager.system.dto.vo.PermissionVO;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.PermissionRequest;
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
            @Mapping(target = "platformType",expression = "java(PlatformType.trance(params.getPlatformType()))")
    })
    PermissionRequest to(PermissionParams params);


    @Mappings({
            @Mapping(target = "platformType",source = "platformType.code")
    })
    PermissionVO toVO(PermissionDTO permissionDTO);

}
