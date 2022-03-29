package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.JwtUtils;
import com.muyuan.system.application.vo.SysRouterVo;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.application.query.SysMenuQuery;
import com.muyuan.system.interfaces.assembler.SysMenuAssembler;
import com.muyuan.system.interfaces.facade.controller.SysRouteController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class SysRouteControllerImpl implements SysRouteController {

    SysMenuQuery sysMenuQuery;

    @Override
    public Result<List<SysRouterVo>> getRouter() {
        List<String> roles = JwtUtils.getRoles();
        List<SysMenu> menus = sysMenuQuery.selectMenuByRoleNames(roles);
        return ResultUtil.success(SysMenuAssembler.buildMenus(SysMenuAssembler.buildMenuTree(menus)));
    }
}
