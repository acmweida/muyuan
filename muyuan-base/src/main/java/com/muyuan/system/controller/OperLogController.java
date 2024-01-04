package com.muyuan.system.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.dto.OperLogParams;
import com.muyuan.system.dto.OperLogQueryParams;
import com.muyuan.system.dto.converter.OperLogConverter;
import com.muyuan.system.dto.vo.OperLogVO;
import com.muyuan.system.service.OperLogService;
import com.muyuan.user.api.dto.OperLogDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName OperLogController
 * Description
 * @author ${author}
 * @date 2022-12-15T15:27:12.638+08:00
 * @Version 1.0
 */
@RestController
@Tag(name = "操作日志记录接口")
@RequestMapping("/log")
@AllArgsConstructor
public class OperLogController {

    private OperLogService operLogService;

    private OperLogConverter converter;

    @GetMapping("/list")
    @Operation(summary = "操作日志记录列表查询")
    @RequirePermissions(value = "system:log:query")
    public Result<Page<OperLogVO>> list(@ModelAttribute OperLogQueryParams params) {
        Page<OperLogDTO> page = operLogService.list(params);

        return ResultUtil.success(Page.copy(page, converter.toVO(page.getRows())));
    }

    @GetMapping("/{id}")
    @Operation(summary = "操作日志记录详情查询")
    @Parameters(
            {@Parameter(name = "id", description = "操作日志记录主键", in = ParameterIn.PATH)}
    )
    @RequirePermissions(value = "common:log:query")
    public Result<OperLogVO> detail(@PathVariable("id") Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<OperLogDTO> hander = operLogService.get(id);

        return hander.map(operLogDTO -> ResultUtil.success(converter.toVO(operLogDTO)))
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    @PostMapping()
    @Operation(summary = "操作日志记录新增")
    //    //    @OperationSupport(ignoreParameters = "id")
    @RequirePermissions(value = "common:log:add")
    @Log(title = "操作日志记录", businessType = BusinessType.INSERT)
    public Result add(@RequestBody @Validated(OperLogParams.Add.class) OperLogParams params) {
        return operLogService.add(converter.to(params));
    }

    @DeleteMapping("/{ids}")
    @Operation(summary = "操作日志记录删除")
    @RequirePermissions(value = "common:log:remove")
    @Log(title = "操作日志记录", businessType = BusinessType.DELETE)
    @Parameters(
            {@Parameter(name = "ids", description = "操作日志记录主键", in = ParameterIn.PATH)}
    )
    public Result delete(@PathVariable String... ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                if (!StringUtils.isNumeric(id)) {
                    return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR);
                }
            }
        }

        return operLogService.deleteById(Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList()).toArray(new Long[0]));
    }

}
