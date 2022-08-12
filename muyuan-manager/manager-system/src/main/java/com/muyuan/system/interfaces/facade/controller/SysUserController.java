package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.application.service.SysUserApplicationService;
import com.muyuan.system.domains.vo.SysUserVO;
import com.muyuan.system.domains.model.SysMenu;
import com.muyuan.system.domains.model.SysUser;
import com.muyuan.system.domains.service.SysUserDomainService;
import com.muyuan.system.domains.dto.SysUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;

@RestController()
@Api(tags = {"系统用户接口"})
@AllArgsConstructor
public class SysUserController {

    private SysUserApplicationService sysUserApplicationService;

    private SysUserDomainService sysUserDomainService;

    @RequirePermissions("system:user:list")
    @GetMapping("/user/list")
    @ApiOperation(value = "用户列表查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name",value = "用户名称",dataTypeClass = String.class,paramType = "query"),
                    @ApiImplicitParam(name = "phone",value = "手机号",dataTypeClass = String.class,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "状态",dataTypeClass = String.class,paramType = "query"),
                    @ApiImplicitParam(name = "startCreateTime",value = "",dataType = "String" ,dataTypeClass = String.class,format = "yyyy-MM-dd HH:mm:ss",paramType = "query"),
                    @ApiImplicitParam(name = "endCreateTime",value = "",dataType = "String", dataTypeClass = String.class,format = "yyyy-MM-dd HH:mm:ss",paramType = "query")
            }
    )
    public Result<Page<List<SysMenu>>> list(@ModelAttribute SysUserDTO sysUserDTO) {
        Page<SysUser> list = sysUserDomainService.list(sysUserDTO);
        return ResultUtil.success(list);
    }

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取指定用户信息")
    public Result<SysUserVO> getUserInfo() {
        final Optional<SysUserVO> userInfo = sysUserApplicationService.getUserInfo();
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        return ResultUtil.success(userInfo.get());
    }

    @RequirePermissions("system:user:get")
    @GetMapping({"/user/{id}","/user/"})
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id",value = "用户ID",dataTypeClass = String.class,paramType = "path",required = true)}
    )
    public Result<SysUserVO> get(@PathVariable(required = false) @Pattern(regexp = "\\d*",message = "用户ID格式错误") String id) {
        final Optional<SysUserVO> userInfo = sysUserApplicationService.get(ObjectUtils.isEmpty(id) ? SecurityUtils.getUserId() :  Long.valueOf(id));
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        return ResultUtil.success(userInfo.get());
    }

    @ApiOperation(value = "系统用户新增", code = 0)
    @RequirePermissions("system:user:add")
    @PostMapping("/user")
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
    public Result add(@RequestBody @Validated SysUserDTO sysUserDTO) {
        if (GlobalConst.NOT_UNIQUE.equals(sysUserDomainService.checkAccountNameUnique(new SysUser(sysUserDTO.getUsername())))) {
            return ResultUtil.fail("账号已存在");
        }

        sysUserDomainService.add(sysUserDTO);
        return ResultUtil.success("注册成功");

    }
}
