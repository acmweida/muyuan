package com.muyuan.config.service;

import com.muyuan.common.bean.Page;
import com.muyuan.config.entity.Config;
import com.muyuan.config.face.dto.ConfigCommand;
import com.muyuan.config.face.dto.ConfigQueryCommand;

import java.util.Optional;

/**
 * @ClassName ConfigService 接口
 * Description 参数配置接口
 * @author ${author}
 * @date 2022-11-29T16:27:55.007+08:00
 * @Version 1.0
 */
public interface ConfigService {

    /**
     * 参数配置分页查询
     * @param commend
     * @return
     */
    Page<Config> list(ConfigQueryCommand commend);

    /**
     * 唯一性检查
     * @param identify
     * @return
     */
    String checkUnique(Config.Identify identify);

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
    boolean updateMenu(ConfigCommand command);

    /**
     * 删除参数配置信息
     * @param ids
     * @return
     */
    boolean deleteConfigById(Long... ids);
}
