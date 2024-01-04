package com.muyuan.system.dto.converter;

import com.muyuan.system.dto.MenuParams;
import com.muyuan.system.dto.vo.MenuVO;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.MenuRequest;
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
public interface MenuConverter {

    @Mappings({
            @Mapping(target = "platformType",source = "platformType.code")
    })
    MenuVO toVO(MenuDTO menuDTO);

    List<MenuVO> toVO(List<MenuDTO> menuDTO);

    MenuDTO to(MenuParams params);


    MenuRequest toRequest(MenuParams params);
}
