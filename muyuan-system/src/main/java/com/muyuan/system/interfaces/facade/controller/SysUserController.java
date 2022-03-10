package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.system.domain.vo.SysUserVO;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/user")
@Api(tags = {"系统用户接口"})
public interface SysUserController {

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    Result<SysUserVO> getUserInfo();

    @ApiOperation(value = "账号密码注册",code = 0)
    @PostMapping("/accountRegister")
    Result accountRegister(@RequestBody @Validated RegisterDTO register);
}
