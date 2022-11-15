package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.infrastructure.repo.dataobject.MenuDO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @ClassName OperatorConverter
 * Description DO转换
 * @Author 2456910384
 * @Date 2022/9/14 10:38
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    @Mappings({
            @Mapping(source = "id",target ="id.value"),
            @Mapping(target = "platformType",expression = "java(MenuConverter.map(menuDO.getPlatformType()))")
    })
    Menu to(MenuDO menuDO);

    static PlatformType map(String id) {
        return StringUtils.isEmpty(id) ? PlatformType.OPERATOR : PlatformType.trance(id);
    }

    List<Menu> to(List<MenuDO> menuDOS);

    @Mappings({
            @Mapping(source = "id.value",target ="id")
    })
    MenuDO to(Menu menuDO);

}
