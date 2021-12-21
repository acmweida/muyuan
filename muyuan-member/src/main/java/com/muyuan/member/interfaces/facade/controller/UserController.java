package com.muyuan.member.interfaces.facade.controller;

import com.muyuan.common.result.Result;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import com.muyuan.member.interfaces.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController()
@RequestMapping(value = "/user")
@Api(tags = {"用户接口"})
public interface UserController {

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    Result getUserInfo();

    @GetMapping("/getUserByUsername")
    @ApiOperation(value = "通过账号获取用户信息")
    Result<UserDTO> getUserByUsername(@RequestParam("username") @NotBlank String username);

    @ApiOperation(value = "账号密码注册",code = 0)
    @PostMapping("/accountRegister")
    Result accountRegister(@RequestBody @Validated RegisterDTO register);
}
