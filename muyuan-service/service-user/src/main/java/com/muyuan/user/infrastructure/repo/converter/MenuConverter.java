package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.user.domain.model.entity.user.Menu;
import com.muyuan.user.infrastructure.repo.dataobject.MenuDO;
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
            @Mapping(source = "id",target ="id.value")
    })
    Menu to(MenuDO menuDO);

    List<Menu> to(List<MenuDO> permissionDOS);

}
