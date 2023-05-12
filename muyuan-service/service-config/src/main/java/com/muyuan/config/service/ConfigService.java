package com.muyuan.config.service;

import com.muyuan.common.bean.Page;
import com.muyuan.config.entity.Config;
import com.muyuan.config.face.dto.ConfigCommand;
import com.muyuan.config.face.dto.ConfigQueryCommand;

import java.util.Optional;

/**
 * @ClassName ConfigDomainService 接口
 * Description 参数配置接口
 * @author wd
 * @date 2022-11-30T09:54:07.407+08:00
 * @Version 1.0
 */
public interface ConfigService {

    /**
     * 参数配置分页查询
     * @param command
     * @return
     */
    Page<Config> list(ConfigQueryCommand command);

    /**
     * 唯一性检查
     * @param identify
     * @return
     */
    boolean exists(Config.Identify identify);

    /**
     * 新增参数配置信息
     * @param command
     * @return
     */
    boolean addConfig(ConfigCommand command);

    /**
     * 查询参数配置信息
     * @param id
     * @return
     */
    Optional<Config> getConfig(Long id);

    /**
     * 更新参数配置信息
     * @param command
     * @return
     */
    boolean updateConfig(ConfigCommand command);

    /**
     * 删除参数配置信息
     * @param ids
     * @return
     */
    boolean deleteConfigById(Long... ids);
}
