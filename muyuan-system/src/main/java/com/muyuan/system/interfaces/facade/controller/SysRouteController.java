package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.application.vo.SysRouterVo;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.domain.service.SysMenuDomainService;
import com.muyuan.system.interfaces.assembler.SysMenuAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"系统路由接口"})
@AllArgsConstructor
public class SysRouteController {

    private SysMenuDomainService sysMenuDomainService;

    @GetMapping("/route")
    @ApiOperation(value = "路由信息获取")
    Result<List<SysRouterVo>> getRouter() {
        List<String> roles = SecurityUtils.getRoles();
        List<SysMenu> menus = sysMenuDomainService.selectMenuByRoleNames(roles);
        return ResultUtil.success(SysMenuAssembler.buildMenus(SysMenuAssembler.buildMenuTree(menus)));
    }

}
