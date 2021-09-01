package com.muyuan.member.spi;

import com.muyuan.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
@Api(tags = {"用户接口"})
public interface UserController {

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    Result getUserInfo();
}
