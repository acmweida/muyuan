package com.muyuan.config.face.dto.mapper;

import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.config.api.dto.ConfigQueryRequest;
import com.muyuan.config.api.dto.ConfigRequest;
import com.muyuan.config.entity.Config;
import com.muyuan.config.face.dto.ConfigCommand;
import com.muyuan.config.face.dto.ConfigQueryCommand;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName OperatorMapper
 * Description DTO 转换
 * @Author 2456910384
 * @Date 2022/9/14 9:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ConfigMapper {

    ConfigQueryCommand toCommand(ConfigQueryRequest request);

    ConfigCommand toCommand(ConfigRequest request);

    List<ConfigDTO> toDTO(List<Config> config);

    ConfigDTO toDTO(Config config);

}
