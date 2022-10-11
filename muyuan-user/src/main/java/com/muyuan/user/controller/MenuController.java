package com.muyuan.user.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.user.dto.Menu;
import com.muyuan.user.dto.RouterVO;
import com.muyuan.user.dto.assembler.MenuAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;


/**
 * @ClassName MenuController 接口
 * Description 菜单控制器
 * @Author 2456910384
 * @Date 2022/2/11 15:36
 * @Version 1.0
 */
@RestController
@Api(tags = {"用户菜单接口"})
@AllArgsConstructor
public class MenuController {


    @GetMapping("/menu/route")
    @ApiOperation(value = "路由信息获取")
    public Result<List<RouterVO>> getRouter() {
        List<String> roles = SecurityUtils.getRoles();
        List<Menu> menus = new LinkedList<>();
        return ResultUtil.success(MenuAssembler.buildMenus(MenuAssembler.buildMenuTree(menus)));
    }

}
