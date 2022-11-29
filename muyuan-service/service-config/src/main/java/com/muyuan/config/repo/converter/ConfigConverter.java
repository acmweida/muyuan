package com.muyuan.config.repo.converter;

import com.muyuan.config.entity.Config;
import com.muyuan.config.repo.dataobject.ConfigDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName OperatorConverter
 * Description DO转换
 * @Author 2456910384
 * @Date 2022/9/14 10:38
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ConfigConverter {

    Config to(ConfigDO configDO);

    List<Config> to(List<ConfigDO> configDOS);

    ConfigDO to(Config config);

}
