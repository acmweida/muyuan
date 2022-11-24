package com.muyuan.manager.system.dto.converter;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.manager.system.dto.RoleParams;
import com.muyuan.manager.system.dto.vo.RoleVO;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleRequest;
import org.apache.commons.lang3.ObjectUtils;
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
public interface RoleConverter {

    @Mappings({
            @Mapping(target = "platformType",source = "platformType.code")
    })
    RoleVO toVO(RoleDTO roleDTO);

    List<RoleVO> toVO(List<RoleDTO> roleDTO);

    static PlatformType map(Integer type) {
        return ObjectUtils.isEmpty(type) ? PlatformType.OPERATOR : PlatformType.trance(type);
    }

    @Mappings({
            @Mapping(target = "platformType",expression = "java(RoleConverter.map(params.getPlatformType()))")
    })
    RoleRequest to(RoleParams params);

}
