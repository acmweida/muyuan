package com.muyuan.common.interfaces.facade.controller;

import com.muyuan.common.domains.vo.TokenVO;
import com.muyuan.common.enums.TokenStatus;
import com.muyuan.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @ClassName TokenController
 * Description 请求token获取和校验
 * @Author 2456910384
 * @Date 2021/10/14 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/token")
@Api(tags = "token公共接口")
public interface TokenController {

    @GetMapping("/get")
    @ApiOperation(value = "token获取")
    Result<TokenVO> getToken();

    @GetMapping("/verfy")
    @ApiOperation(value = "token校验")
    Result<TokenStatus> verify(@RequestParam("token") @NotNull(message = "token不能为null") String token);
}
