package com.muyuan.manager.system.facade.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.system.dto.PermissionParams;
import com.muyuan.manager.system.dto.PermissionQueryParams;
import com.muyuan.manager.system.dto.converter.PermissionConverter;
import com.muyuan.manager.system.dto.vo.PermissionVO;
import com.muyuan.manager.system.service.PermissionService;
import com.muyuan.user.api.dto.PermissionDTO;
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

@RestController
@Api(tags = {"权限数据接口"})
@AllArgsConstructor
public class PermissionController {

    private PermissionService permissionService;

    private PermissionConverter converter;

    @GetMapping("/permission/list")
    @ApiOperation(value = "字典数值列表查询")
    @RequirePermissions(value = "system:perms:query")
    public Result<Page<PermissionVO>> list(@ModelAttribute PermissionQueryParams params) {

        Page<PermissionDTO> page = permissionService.list(params);

        return ResultUtil.success(Page.copy(page, converter.toVO(page.getRows())));

    }

    @GetMapping("/permission/{id}")
    @ApiOperation(value = "字典数据详情数值查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "权限主键", dataTypeClass = Long.class, paramType = "path", required = true)}
    )
    @RequirePermissions(value = "system:perms:query")
    public Result<PermissionVO> detail(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<PermissionDTO> hander = permissionService.get(id);

        return hander.map(permissionDTO -> ResultUtil.success(converter.toVO(permissionDTO)))
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }


    @PutMapping("/permission")
    @ApiOperation(value = "权限信息更新")
    @RequirePermissions(value = "system:perms:edit")
    public Result update(@RequestBody @Validated(PermissionParams.Update.class) PermissionParams params) {
        if (ObjectUtils.isEmpty(params.getId())) {
            return ResultUtil.fail("id is null");
        }

        return permissionService.update(converter.to(params));
    }

    @DeleteMapping("/permission/{ids}")
    @ApiOperation(value = "权限信息删除")
    @RequirePermissions(value = "system:perms:remove")
    @Log(title = "权限信息", businessType = BusinessType.DELETE)
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "ids", value = "字典数据主键", dataTypeClass = String.class, paramType = "path", required = true)}
    )
    public Result delete(@PathVariable String... ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                if (!StringUtils.isNumeric(id)) {
                    return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR);
                }
            }
        }

        return permissionService.deleteById(Arrays.stream(ids).map(Long::parseLong).toArray(Long[]::new));
    }


    @PostMapping("/permission")
    @ApiOperation(value = "权限新增")
    @ApiOperationSupport(ignoreParameters = "id")
    @RequirePermissions(value = "system:perms:add")
    public Result add(@RequestBody @Validated(PermissionParams.Add.class) PermissionParams params) {
        return permissionService.add(converter.to(params));
    }


}
