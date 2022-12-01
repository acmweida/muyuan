package com.muyuan.config.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.config.api.dto.ConfigQueryRequest;
import com.muyuan.config.api.dto.ConfigRequest;

/**
 * 参数配置Service接口
 *
 * @author wd
 * @date 2022-11-30T09:54:07.407+08:00
 */
public interface ConfigInterface {

    /**
      * 查询参数配置列表
      * @param request
      * @return
      */
    Result<Page<ConfigDTO>> list(ConfigQueryRequest request);

    /**
     * 添加参数配置
     * @param request
     * @return
     */
    Result add(ConfigRequest request);

    /**
     * 查询参数配置
     * @param id
     * @return
     */
    Result<ConfigDTO> getConfig(Long id);

    /**
     * 更新参数配置
     * @param request
     * @return
     */
    Result updateConfig(ConfigRequest request);

    /**
     *  删除参数配置
     * @param ids
     * @return
     */
    Result deleteConfig(Long... ids);

}
