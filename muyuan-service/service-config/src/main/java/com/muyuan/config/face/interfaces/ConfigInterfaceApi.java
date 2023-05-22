package com.muyuan.config.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.config.api.ConfigInterface;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.config.api.dto.ConfigQueryRequest;
import com.muyuan.config.api.dto.ConfigRequest;
import com.muyuan.config.entity.Config;
import com.muyuan.config.face.dto.transfer.ConfigTransfer;
import com.muyuan.config.service.ConfigService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Optional;

/**
 * @ClassName ConfigInterfaceApi
 * Description 内部接口  参数配置
 * @author wd
 * @date 2022-11-30T09:54:07.407+08:00
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.CONFIG, version = "1.0"
        , interfaceClass = ConfigInterface.class
)
public class ConfigInterfaceApi implements ConfigInterface {

    private ConfigTransfer MAPPER;

    private ConfigService configDomainService;

    @Override
    public Result<Page<ConfigDTO>> list(ConfigQueryRequest request) {
        Page<Config> list = configDomainService.list(MAPPER.toCommand(request));

        return ResultUtil.success( Page.copy(list,MAPPER.toDTO(list.getRows())));
    }

    @Override
    public Result add(ConfigRequest request) {
        if (configDomainService.exists(new Config.Identify(request.getId()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = configDomainService.addConfig(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result<ConfigDTO> getConfig(Long id) {
        Optional<Config> handler = configDomainService.getConfig(id);

        return handler.map(MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result updateConfig(ConfigRequest request) {
        if (configDomainService.exists(new Config.Identify(request.getId()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }

        boolean flag = configDomainService.updateConfig(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result deleteConfig(Long... ids) {
        if (configDomainService.deleteConfigById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
