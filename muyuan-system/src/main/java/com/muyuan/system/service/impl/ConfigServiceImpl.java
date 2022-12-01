package com.muyuan.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.config.api.ConfigInterface;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.config.api.dto.ConfigQueryRequest;
import com.muyuan.system.service.ConfigService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName ConfigServiceImpl
 * Description 参数配置
 * @Author  wd
 * @Date 2022-11-30T10:41:23.089+08:00
 * @Version 1.0
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @DubboReference(group = ServiceTypeConst.CONFIG, version = "1.0")
    private ConfigInterface  configInterface;

    @Override
    public Optional<ConfigDTO> get(String configKey) {
        return Optional.of(configKey)
                .map(configKey_ -> {
                    Result<Page<ConfigDTO>> configHander = configInterface.list(ConfigQueryRequest
                            .builder()
                            .configKey(configKey)
                            .build());
                    if (ResultUtil.isSuccess(configHander)) {
                        if (ObjectUtils.isNotEmpty(configHander.getData().getRows())) {
                            return configHander.getData().getRows().get(0);
                        }
                    }
                    return null;
                });
    }


}
