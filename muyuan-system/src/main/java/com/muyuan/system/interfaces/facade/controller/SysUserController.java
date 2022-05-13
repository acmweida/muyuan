package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.application.service.SysUserApplicationService;
import com.muyuan.system.application.vo.SysUserVO;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.service.SysUserDomainService;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import com.muyuan.system.interfaces.dto.SysUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            {@ApiImplicitParam(name = "name",value = "用户名称",dataType = "String",paramType = "query"),
                    @ApiImplicitParam(name = "phone",value = "手机号",dataType = "String",paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "状态",dataType = "String",paramType = "query"),
                    @ApiImplicitParam(name = "startCreateTime",value = "",dataType = "String" ,format = "yyyy-MM-dd HH:mm:ss",paramType = "query"),
                    @ApiImplicitParam(name = "endCreateTime",value = "",dataType = "String" ,format = "yyyy-MM-dd HH:mm:ss",paramType = "query")
            }
    )
    public Result<Page<List<SysMenu>>> list(@ModelAttribute SysUserDTO sysUserDTO) {
        Page<SysUser> list = sysUserDomainService.list(sysUserDTO);
        return ResultUtil.success(list);
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取用户信息")
    public Result<SysUserVO> getUserInfo() {
        final Optional<SysUserVO> userInfo = sysUserApplicationService.getUserInfo();
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        return ResultUtil.success(userInfo.get());
    }

    @ApiOperation(value = "账号密码注册", code = 0)
    @PostMapping("/user")
    public Result add(@RequestBody @Validated RegisterDTO register) {
        if (GlobalConst.NOT_UNIQUE.equals(sysUserDomainService.checkAccountNameUnique(new SysUser(register.getUsername())))) {
            return ResultUtil.fail("账号已存在");
        }

        sysUserDomainService.add(register);
        return ResultUtil.success("注册成功");

    }
}
