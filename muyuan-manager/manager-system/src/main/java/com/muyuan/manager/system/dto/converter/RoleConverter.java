package com.muyuan.manager.system.dto.converter;

import com.muyuan.manager.system.dto.vo.RoleVO;
import com.muyuan.user.api.dto.RoleDTO;
import org.mapstruct.Mapper;

/**
 * @ClassName MenuConverter
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 17:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    RoleVO toVO(RoleDTO roleDTO);

}
