package com.muyuan.user.face.dto.mapper;

import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleQueryRequest;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.face.dto.RoleQueryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleQueryCommand toCommand(RoleQueryRequest request);

    @Mappings({
            @Mapping(target = "id",source = "id.value")
    })
    RoleDTO toDTO(Role role);

    List<RoleDTO> toDTO(List<Role> role);

}
