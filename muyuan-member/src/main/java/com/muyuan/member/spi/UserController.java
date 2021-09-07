package com.muyuan.member.spi;

import com.muyuan.common.result.Result;
import com.muyuan.member.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
@Api(tags = {"用户接口"})
public interface UserController {

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    Result getUserInfo();

    @ApiOperation(value = "账号密码注册",code = 0)
    @PostMapping("/accountRegister")
    Result accountRegister(@RequestBody @Validated RegisterDTO register);
}
