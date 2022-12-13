package com.muyuan.user.dto.converter;

import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.dto.UserVO;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserConverter 接口
 * Description UserConverter
 * @Author 2456910384
 * @Date 2022/10/11 14:46
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mapping(target = "roles", expression = "java(UserConverter.map(operatorDTO.getRoles()))")
    UserVO toVO(OperatorDTO operatorDTO);

    @Mapping(target = "roles", expression = "java(UserConverter.map(userDTO.getRoles()))")
    UserVO toVO(UserDTO userDTO);

    static List<String> map(List<RoleDTO> value) {
        return ObjectUtils.isEmpty(value) ? null : value.stream().map(RoleDTO::getCode).collect(Collectors.toList());
    }
}
