package com.muyuan.manager.system.dto.converter;

import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.config.api.dto.ConfigRequest;
import com.muyuan.manager.system.dto.ConfigParams;
import com.muyuan.manager.system.dto.vo.ConfigVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName ConfigConverter
 * Description
 * @Author  wd
 * @Date 2022-11-30T09:54:07.407+08:00
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ConfigConverter {

    List<ConfigVO> toVO(List<ConfigDTO> configVOS);

    ConfigRequest to(ConfigParams params);

    ConfigVO toVO(ConfigDTO configDTO);

}
