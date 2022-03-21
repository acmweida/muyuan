package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.system.application.vo.SysRouterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"系统路由接口"})
public interface SysRouteController {

    @GetMapping("/route")
    @ApiOperation(value = "路由信息获取")
    Result<List<SysRouterVo>> getRouter();

}
