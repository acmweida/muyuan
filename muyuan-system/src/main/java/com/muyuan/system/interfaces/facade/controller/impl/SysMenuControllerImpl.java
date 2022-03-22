package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.JwtUtils;
import com.muyuan.system.application.vo.SysRouterVo;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.application.query.SysMenuQuery;
import com.muyuan.system.interfaces.assembler.SysMenuAssembler;
import com.muyuan.system.interfaces.facade.controller.SysMenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MenuControllerImpl
 * Description MenuControllerImpl 菜单控制器实现
 * @Author 2456910384
 * @Date 2022/2/11 16:12
 * @Version 1.0
 */
@Component
public class SysMenuControllerImpl implements SysMenuController {


    @Autowired
    SysMenuQuery sysMenuQuery;

    @Override
    public Result<List<SysRouterVo>> getRouter() {
        List<String> roles = JwtUtils.getRoles();
        List<SysMenu> menus = sysMenuQuery.selectMenuByRoleNames(roles);
        return ResultUtil.success(SysMenuAssembler.buildMenus(SysMenuAssembler.buildMenuTree(menus)));
    }
}
