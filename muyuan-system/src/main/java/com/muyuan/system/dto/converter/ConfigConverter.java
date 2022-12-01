package com.muyuan.system.dto.converter;

import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.system.dto.vo.ConfigVO;
import org.mapstruct.Mapper;

/**
 * @ClassName ConfigConverter
 * Description
 * @Author  wd
 * @Date 2022-11-30T09:54:07.407+08:00
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ConfigConverter {

    ConfigVO toVO(ConfigDTO configDTO);

}
