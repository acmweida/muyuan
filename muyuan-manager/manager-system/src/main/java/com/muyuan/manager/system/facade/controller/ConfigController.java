package com.muyuan.manager.system.facade.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.config.api.dto.ConfigDTO;
import com.muyuan.manager.system.dto.ConfigParams;
import com.muyuan.manager.system.dto.ConfigQueryParams;
import com.muyuan.manager.system.dto.converter.ConfigConverter;
import com.muyuan.manager.system.dto.vo.ConfigVO;
import com.muyuan.manager.system.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @GetMapping("/list")
    @ApiOperation(value = "参数配置列表查询")
    @RequirePermissions(value = "system:config:query")
    public Result<Page<ConfigVO>> list(@ModelAttribute ConfigQueryParams params) {
        Page<ConfigDTO> page = configService.list(params);

        return ResultUtil.success(Page.copy(page, converter.toVO(page.getRows())));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "参数配置详情查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "参数配置主键", dataTypeClass = Long.class, paramType = "path", required = true)}
    )
    @RequirePermissions(value = "system:config:query")
    public Result<ConfigVO> detail(@PathVariable("id") Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<ConfigDTO> hander = configService.get(id);

        return hander.map(configDTO -> ResultUtil.success(converter.toVO(configDTO)))
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    @PostMapping()
    @ApiOperation(value = "参数配置新增")
    @ApiOperationSupport(ignoreParameters = "id")
    @RequirePermissions(value = "system:config:add")
    @Log(title = "参数配置", businessType = BusinessType.INSERT)
    public Result add(@RequestBody @Validated(ConfigParams.Add.class) ConfigParams params) {
        return configService.add(converter.to(params));
    }

    @PutMapping()
    @ApiOperation(value = "参数配置更新")
    @RequirePermissions(value = "system:config:edit")
    @Log(title = "参数配置", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody @Validated(ConfigParams.Update.class) ConfigParams params) {
        if (ObjectUtils.isEmpty(params.getId())) {
            return ResultUtil.fail("id is null");
        }

        return configService.update(converter.to(params));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "参数配置删除")
    @RequirePermissions(value = "system:config:remove")
    @Log(title = "参数配置", businessType = BusinessType.DELETE)
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "ids", value = "参数配置主键", dataTypeClass = Long.class, paramType = "path", required = true)}
    )
    public Result delete(@PathVariable String... ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                if (!StringUtils.isNumeric(id)) {
                    return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR);
                }
            }
        }

        return configService.deleteById(Arrays.stream(ids).map(Long::parseLong).toArray(Long[]::new));
    }

}
