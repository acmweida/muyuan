package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.system.application.service.SysUserApplicationService;
import com.muyuan.system.application.vo.SysUserVO;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.service.SysUserDomainService;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@Api(tags = {"系统用户接口"})
@AllArgsConstructor
public class SysUserController {

    private SysUserApplicationService sysUserApplicationService;

    private SysUserDomainService sysUserDomainService;

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
        if (GlobalConst.UNIQUE.equals(sysUserDomainService.checkAccountNameUnique(new SysUser(register.getUsername())))) {
            return ResultUtil.fail("账号已存在");
        }

        sysUserDomainService.add(register);
        return ResultUtil.success("注册成功");

    }
}
