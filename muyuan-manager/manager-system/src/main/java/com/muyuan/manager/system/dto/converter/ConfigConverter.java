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
 * @Author  ${author}
 * @Date 2022-11-29T16:27:55.007+08:00
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ConfigConverter {


    List<ConfigVO> toVO(List<ConfigDTO> configVOS);

    ConfigRequest to(ConfigParams params);

    ConfigVO toVO(ConfigDTO configDTO);

}
