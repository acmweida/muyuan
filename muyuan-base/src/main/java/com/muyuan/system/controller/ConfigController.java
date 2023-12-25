package com.muyuan.system.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.system.dto.converter.ConfigConverter;
import com.muyuan.system.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "参数配置接口")
@RequestMapping("/config")
@AllArgsConstructor
public class ConfigController {

    private ConfigService configService;

    private ConfigConverter converter;

    @GetMapping("/{key}")
    @Operation(summary  = "参数配置详情查询")
    @Parameters(
            {@Parameter(name = "key", description = "参数配置主键", in = ParameterIn.PATH, required = true)}
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
