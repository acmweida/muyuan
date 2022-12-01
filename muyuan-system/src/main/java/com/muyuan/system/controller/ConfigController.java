package com.muyuan.system.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.system.dto.converter.ConfigConverter;
import com.muyuan.system.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @ClassName ConfigController
 * Description
 * @author wd
 * @date 2022-11-30T09:54:07.407+08:00
 * @Version 1.0
 */
@RestController
@Api(tags = {"参数配置接口"})
@RequestMapping("/config")
@AllArgsConstructor
public class ConfigController {

    private ConfigService configService;

    private ConfigConverter converter;

    @GetMapping("/{key}")
    @ApiOperation(value = "参数配置详情查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "key", value = "参数配置主键", dataTypeClass = String.class, paramType = "path", required = true)}
    )
    @RequirePermissions(value = "system:config:query")
    public Result<String> detail(@PathVariable("key") String key) {
        if (ObjectUtils.isEmpty(key)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<ConfigDTO> hander = configService.get(key);

        return hander.map(configDTO -> ResultUtil.success(ResponseCode.SUCCESS.getMsg(),configDTO.getConfigValue()))
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

}
