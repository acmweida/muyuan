package com.muyuan.config.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.config.api.ConfigInterface;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.config.api.dto.ConfigQueryRequest;
import com.muyuan.config.api.dto.ConfigRequest;
import com.muyuan.config.entity.Config;
import com.muyuan.config.face.dto.mapper.ConfigMapper;
import com.muyuan.config.service.ConfigService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Optional;

/**
 * @ClassName ConfigInterfaceApi
 * Description 内部接口  参数配置
 * @author ${author}
 * @date 2022-11-29T16:27:55.007+08:00
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.CONFIG, version = "1.0"
        , interfaceClass = ConfigInterface.class
)
public class ConfigInterfaceApi implements ConfigInterface {

    private ConfigMapper MAPPER;

    private ConfigService configService;

    @Override
    public Result<Page<ConfigDTO>> list(ConfigQueryRequest request) {
        Page<Config> list = configService.list(MAPPER.toCommand(request));

        return ResultUtil.success( Page.copy(list,MAPPER.toDTO(list.getRows())));
    }

    @Override
    public Result add(ConfigRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(configService.checkUnique(new Config.Identify(request.getId())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = configService.addConfig(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result<ConfigDTO> getConfig(Long id) {
        Optional<Config> handler = configService.getConfig(id);

        return handler.map(MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result updateConfig(ConfigRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(configService.checkUnique(new Config.Identify(request.getId())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }

        boolean flag = configService.updateMenu(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result deleteConfig(Long... ids) {
        if (configService.deleteConfigById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
