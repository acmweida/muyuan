package com.muyuan.manager.system.dto.converter;

import com.muyuan.manager.system.dto.vo.MenuVO;
import com.muyuan.user.api.dto.MenuDTO;
import org.mapstruct.Mapper;

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

    MenuVO toVO(MenuDTO menuDTO);

    List<MenuVO> toVO(List<MenuDTO> menuDTO);
}
