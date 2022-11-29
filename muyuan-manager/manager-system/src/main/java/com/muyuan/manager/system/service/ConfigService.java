package com.muyuan.manager.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.config.api.dto.ConfigRequest;
import com.muyuan.manager.system.dto.ConfigQueryParams;

import java.util.Optional;

/**
 * @ClassName ConfigService
 * Description 参数配置服务
 * @Author  ${author}
 * @Date 2022-11-29T16:27:55.007+08:00
 * @Version 1.0
 */
public interface ConfigService {

    /**
     * 查询参数配置
     * @param params
     * @return
     */
    Page<ConfigDTO> list(ConfigQueryParams params);

    /**
     * 参数配置添加
     * @param request
     */
    Result add(ConfigRequest request);

    /**
     * 参数配置查询
     * @param id
     * @return
     */
    Optional<ConfigDTO> get(Long id);

    /**
     * 参数配置变更
     * @param request
     * @return
     */
    Result update(ConfigRequest request);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result deleteById(Long... ids);
}
