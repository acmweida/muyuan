package com.muyuan.manager.system.facade.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.system.dto.OperatorParams;
import com.muyuan.manager.system.dto.OperatorQueryParams;
import com.muyuan.manager.system.model.SysUser;
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

import javax.validation.constraints.Pattern;
import java.util.Optional;

@RestController()
@Api(tags = {"系统用户接口"})
@AllArgsConstructor
public class OperatorController {

    private OperatorService operatorService;

    @RequirePermissions("system:operator:query")
    @GetMapping("/operator/list")
    @ApiOperation(value = "用户列表查询")
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
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "username",value = "用户名,用户名只能由字母、数字、下划线组成，且长度是4-16位",dataTypeClass = String.class,paramType = "body",required = true),
                    @ApiImplicitParam(name = "password",value = "用户密码,最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符",dataTypeClass = String.class,paramType = "body",required = true),
                    @ApiImplicitParam(name = "nickName",value = "用户昵称",dataTypeClass = String.class,paramType = "body",required = true),
                    @ApiImplicitParam(name = "deptId",value = "部门ID",dataTypeClass = Long.class,paramType = "body"),
                    @ApiImplicitParam(name = "email",value = "邮箱",dataTypeClass = String.class,paramType = "body"),
                    @ApiImplicitParam(name = "phone",value = "手机号",dataTypeClass = String.class,paramType = "body"),
                    @ApiImplicitParam(name = "status",value = "状态",dataTypeClass = String.class,paramType = "body",defaultValue = "0"),
                    @ApiImplicitParam(name = "roleIds",value = "角色ID",dataType = "Long[]",dataTypeClass = Long.class,paramType = "body"),
                    @ApiImplicitParam(name = "sex",value = "性别",dataTypeClass = String.class,paramType = "body",required = true),
                    @ApiImplicitParam(name = "remark",value = "备注",dataTypeClass = String.class,paramType = "body")
            }
    )
    public Result add(@RequestBody @Validated OperatorParams operatorParams) {
        if (GlobalConst.NOT_UNIQUE.equals(operatorService.checkAccountNameUnique(new SysUser(operatorParams.getUsername())))) {
            return ResultUtil.fail("账号已存在");
        }

        operatorService.add(operatorParams);
        return ResultUtil.success("注册成功");

    }
}
