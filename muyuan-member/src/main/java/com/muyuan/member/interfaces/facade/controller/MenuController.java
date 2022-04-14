package com.muyuan.member.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.member.application.query.MenuQuery;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.vo.RouterVo;
import com.muyuan.member.interfaces.assembler.MenuAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class MenuController {

    @Autowired
    MenuQuery menuQuery;

    @GetMapping("/getRouters")
    @ApiOperation(value = "路由信息获取")
    public Result<List<RouterVo>> getRouter() {
        List<String> roles = SecurityUtils.getRoles();
        List<Menu> menus = menuQuery.selectMenuByRoleNames(roles);
        return ResultUtil.success(MenuAssembler.buildMenus(MenuAssembler.buildMenuTree(menus)));
    }



}
