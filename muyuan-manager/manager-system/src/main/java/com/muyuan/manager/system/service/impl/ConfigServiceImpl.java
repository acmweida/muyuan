package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.config.api.ConfigInterface;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.config.api.dto.ConfigQueryRequest;
import com.muyuan.config.api.dto.ConfigRequest;
import com.muyuan.manager.system.dto.ConfigQueryParams;
import com.muyuan.manager.system.service.ConfigService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName ConfigServiceImpl
 * Description 参数配置
 * @Author  ${author}
 * @Date 2022-11-29T16:27:55.007+08:00
 * @Version 1.0
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @DubboReference(group = ServiceTypeConst.CONFIG, version = "1.0")
    private ConfigInterface  configInterface;

    @Override
    public Page<ConfigDTO> list(ConfigQueryParams params) {
        ConfigQueryRequest request = ConfigQueryRequest.builder()
                .id(params.getId())
                .configName(params.getConfigName())
                .configKey(params.getConfigKey())
                .configValue(params.getConfigValue())
                .configType(params.getConfigType())
                .createBy(params.getCreateBy())
                .createTime(params.getCreateTime())
                .updateBy(params.getUpdateBy())
                .updateTime(params.getUpdateTime())
                .remark(params.getRemark())
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }


        Result<Page<ConfigDTO>> res = configInterface.list(request);


        return res.getData();
    }

    @Override
    public Result add(ConfigRequest request) {
        return configInterface.add(request);
    }

    @Override
    public Optional<ConfigDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<ConfigDTO> configHander = configInterface.getConfig(id_);
                    return ResultUtil.getOr(configHander, null);
                });
    }

    @Override
    public Result update(ConfigRequest request) {
        return configInterface.updateConfig(request);
    }

    @Override
    public Result deleteById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return ResultUtil.fail();
        }

        return configInterface.deleteConfig(ids);
    }
}
