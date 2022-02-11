package com.muyuan.member.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.vo.RouterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MenuController 接口
 * Description 菜单控制器
 * @Author 2456910384
 * @Date 2022/2/11 15:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/menu")
@Api(tags = {"菜单接口"})
public interface MenuController {

    @GetMapping("/getRouter")
    @ApiOperation(value = "路由信息获取")
    Result<RouterVo> getRouter();
}
