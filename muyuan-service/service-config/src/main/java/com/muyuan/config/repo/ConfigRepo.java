package com.muyuan.config.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.config.entity.Config;
import com.muyuan.config.face.dto.ConfigQueryCommand;

import java.util.List;

/**
 * 参数配置对象 t_config
 *
 * @author ${author}
 * @date 2022-11-29T16:27:55.007+08:00
 */

public interface ConfigRepo {

    Page<Config> select(ConfigQueryCommand command);

    Config selectConfig(Long id);

    Config selectConfig(Config.Identify identify);

    boolean addConfig(Config config);

    /**
     * 更新信息
     * @param config
     * @return old value
     */
    Config updateConfig(Config config);

    /**
     * 删除
     * @param ids
     * @return old value
     */
    List<Config> deleteBy(Integer... ids);

}
