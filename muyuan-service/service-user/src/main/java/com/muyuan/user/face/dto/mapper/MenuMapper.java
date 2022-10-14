package com.muyuan.user.face.dto.mapper;

import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuQueryRequest;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.face.dto.MenuQueryCommand;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @ClassName MenuMapper
 * Description DTO 转换
 * @Author 2456910384
 * @Date 2022/9/14 9:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mappings({
            @Mapping(target = "roleCodes",expression = "java(MenuMapper.map(request.getRoleCodes()))")
    })
    MenuQueryCommand toCommand(MenuQueryRequest request);

    List<MenuDTO> toDTO(List<Menu> menus);


    @Mappings({
            @Mapping(target = "id", source = "id.value")
    })
    MenuDTO toDTO(Menu menu);

    static RoleCode[] map(String[] valus) {
        if (ObjectUtils.isEmpty(valus)) {
            return new RoleCode[0];
        }
        RoleCode[] roleCodes = new RoleCode[valus.length];
        int size = valus.length;
        for (int i=0;i<size;i++) {
            roleCodes[i] = new RoleCode(valus[i]);
        }
        return roleCodes;
    }

}
