package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.system.application.vo.SysMenuVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = {"系统菜单接口"})
public interface SysMenuController {

    @GetMapping("/menus")
    Result<List<SysMenuVO>> getMenus();


}
