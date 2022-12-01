package com.muyuan.system.service;

import com.muyuan.config.api.dto.ConfigDTO;

import java.util.Optional;

/**
 * @ClassName ConfigService
 * Description 参数配置服务
 * @Author  wd
 * @Date 2022-11-30T09:54:07.407+08:00
 * @Version 1.0
 */
public interface ConfigService {

    /**
     * 参数配置查询
     * @param configKey
     * @return
     */
    Optional<ConfigDTO> get(String configKey);

}
