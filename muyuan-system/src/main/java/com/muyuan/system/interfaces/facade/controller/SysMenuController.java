package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.application.vo.SysMenuVO;
import com.muyuan.system.application.service.SysMenuService;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.interfaces.dto.SysMenuDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;


/**
 * @ClassName MenuController 接口
 * Description 菜单控制器
 * @Author 2456910384
 * @Date 2022/2/11 15:36
 * @Version 1.0
 */
@RestController
@Api(tags = {"系统菜单接口"})
@AllArgsConstructor
public class SysMenuController {

    private SysMenuService sysMenuService;

    @RequirePermissions("system:menu:list")
    @GetMapping("/menu/list")
    @ApiOperation(value = "菜单列表查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name",value = "菜单名称",dataType = "String",paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "状态",dataType = "String",paramType = "query")}
    )
    public Result<List<SysMenuVO>> list(@ModelAttribute SysMenuDTO sysMenuDTO) {
        List<SysMenu> list = sysMenuService.list(sysMenuDTO);
        return ResultUtil.success(list);
    }

    @RequirePermissions("system:menu:edit")
    @GetMapping("/menu/{id}")
    @ApiOperation(value = "获取菜单详情")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id",value = "菜单ID",dataType = "String",paramType = "path",required = true)}
    )
    public Result<SysMenuVO> get(@PathVariable @NotBlank(message = "菜单ID不能为空")
                                             String id) {
        Optional<SysMenu> sysMenu = sysMenuService.get(id);
        if (sysMenu.isPresent()) {
            ResultUtil.success(sysMenu.get());
        }

        return ResultUtil.fail("菜单不存在");
    }

}
