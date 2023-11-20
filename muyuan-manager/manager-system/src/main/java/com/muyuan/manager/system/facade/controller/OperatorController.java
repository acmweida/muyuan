package com.muyuan.manager.system.facade.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.system.dto.OperatorParams;
import com.muyuan.manager.system.dto.OperatorQueryParams;
import com.muyuan.manager.system.dto.converter.OperatorConverter;
import com.muyuan.manager.system.service.OperatorService;
import com.muyuan.user.api.dto.OperatorDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Pattern;
import java.util.Optional;

@RestController()
@Api(tags = {"系统用户接口"})
@AllArgsConstructor
public class OperatorController {

    private OperatorService operatorService;

    private OperatorConverter converter;

    @RequirePermissions("system:operator:query")
    @GetMapping("/operator/list")
    @ApiOperation(value = "用户列表查询")
//    //    @ApiOperationSupport(ignoreParameters = "roleIds")
    public Result<Page<OperatorDTO>> list(@ModelAttribute OperatorQueryParams operatorQueryParams) {
        Page<OperatorDTO> list = operatorService.list(operatorQueryParams);
        return ResultUtil.success(list);
    }

    @RequirePermissions("system:operator:query")
    @GetMapping({"/operator/{id}","/operator/"})
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id",value = "用户ID 不传默认当前用户",dataTypeClass = String.class,paramType = "path")}
    )
    public Result<OperatorDTO> get(@PathVariable(required = false) @Pattern(regexp = "\\d*",message = "用户ID格式错误") String id) {
        final Optional<OperatorDTO> userInfo = operatorService.get(ObjectUtils.isEmpty(id) ? SecurityUtils.getUserId() :  Long.valueOf(id));
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        return userInfo.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    @ApiOperation(value = "系统用户新增", code = 0)
    @RequirePermissions("system:operator:add")
    @PostMapping("/operator")
//    //    @ApiOperationSupport(ignoreParameters = {"id","roleIds"})
    public Result add(@RequestBody @Validated(OperatorParams.Add.class) OperatorParams params) {
        return operatorService.add(converter.to(params));
    }

    @ApiOperation(value = "角色分配用户查询")
    @RequirePermissions("system:operator:query")
    @GetMapping("/role/authUser/allocatedList")
    public Result allocatedList(@ModelAttribute @Validated(OperatorQueryParams.SelectAllow.class) OperatorQueryParams operatorQueryParams) {
        return ResultUtil.success(operatorService.selectAllocatedList(operatorQueryParams));
    }

    @ApiOperation(value = "角色为分配用户查询")
    @RequirePermissions("system:operator:query")
    @GetMapping("/role/authUser/unallocatedList")
    public Result unallocatedList(@ModelAttribute @Validated(OperatorQueryParams.SelectAllow.class) OperatorQueryParams operatorQueryParams) {
        return ResultUtil.success(operatorService.selectUnallocatedList(operatorQueryParams));
    }

    @ApiOperation(value = "角色添加用户")
    @RequirePermissions("system:role:edit")
    @PutMapping("/operator/authRole")
    //    @ApiOperationSupport(ignoreParameters = {"id","roleIds"})
    public Result authRole(@RequestBody @Validated(OperatorParams.AuthRole.class)  OperatorParams params) {
        return  operatorService.authRole(params.getId(),params.getRoleIds());
    }
}
