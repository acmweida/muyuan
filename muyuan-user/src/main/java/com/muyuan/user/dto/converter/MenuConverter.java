package com.muyuan.user.dto.converter;

import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.dto.MenuVO;
import org.mapstruct.Mapper;

/**
 * @ClassName UserConverter 接口
 * Description UserConverter
 * @Author 2456910384
 * @Date 2022/10/11 14:46
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    MenuVO toVO(MenuDTO userDTO);
}
