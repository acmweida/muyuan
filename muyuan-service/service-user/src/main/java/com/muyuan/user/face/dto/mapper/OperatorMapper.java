package com.muyuan.user.face.dto.mapper;

import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.face.dto.OperatorQueryCommand;
import org.apache.commons.lang3.ObjectUtils;
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
@Mapper(componentModel = "spring")
public interface OperatorMapper {

    OperatorQueryCommand toCommand(OperatorQueryRequest request);

    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "username", source = "username.value"),
            @Mapping(target = "roles", expression = "java(OperatorMapper.map(operator.getRoles()))")
    })
    OperatorDTO toDto(Operator operator);

    List<OperatorDTO> toDto(List<Operator> operators);

    static List<String> map(List<Role> value) {
        return ObjectUtils.isEmpty(value) ? null : value.stream().map(Role::getCode).collect(Collectors.toList());
    }

}
