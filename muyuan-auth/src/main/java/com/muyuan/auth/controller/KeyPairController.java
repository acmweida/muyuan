package com.muyuan.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("/rsa")
@Api(tags = {"jwt密钥"})
public interface KeyPairController {

    @ApiOperation(value = "获取jwt公钥")
    @GetMapping("/publicKey")
    Map getPublicKey();
}
