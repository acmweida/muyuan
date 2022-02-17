package com.muyuan.member.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.JwtUtils;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.query.MenuQuery;
import com.muyuan.member.domain.vo.RouterVo;
import com.muyuan.member.interfaces.assembler.MenuAssembler;
import com.muyuan.member.interfaces.facade.controller.MenuController;
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
public class MenuControllerImpl implements MenuController {


    @Autowired
    MenuQuery menuQuery;

    @Override
    public Result<List<RouterVo>> getRouter() {
        List<String> roles = JwtUtils.getRoles();
        List<Menu> menus = menuQuery.selectMenuByRoleNames(roles);
        return ResultUtil.render(MenuAssembler.buildMenus(MenuAssembler.buildMenuTree(menus)));
    }
}
