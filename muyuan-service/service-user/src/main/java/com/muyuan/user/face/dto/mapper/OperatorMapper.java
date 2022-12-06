package com.muyuan.user.face.dto.mapper;

import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;
import com.muyuan.user.api.dto.OperatorRequest;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.face.dto.OperatorCommand;
import com.muyuan.user.face.dto.OperatorQueryCommand;
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
public interface OperatorMapper {

    OperatorQueryCommand toCommand(OperatorQueryRequest request);

    @Mappings({
            @Mapping(target = "roleIds", expression = "java(OperatorMapper.map(request.getRoleIds()))"),
    })
    OperatorCommand toCommand(OperatorRequest request);

    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "username", source = "username.value"),
    })
    OperatorDTO toDto(Operator operator);

    List<OperatorDTO> toDto(List<Operator> operators);

    @Mappings({
            @Mapping(target = "id",source = "id.value")
    })
    RoleDTO toDto(Role role);

    static RoleID[] map(Long[] roleIds) {
        RoleID[] roleIDS = new RoleID[roleIds.length];
        int i = 0;
        for (Long roleId : roleIds) {
            roleIDS[i++] = new RoleID(roleId);
        }
        return roleIDS;
    }

}
