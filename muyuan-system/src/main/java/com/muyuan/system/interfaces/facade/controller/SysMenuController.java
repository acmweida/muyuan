package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.application.vo.SysMenuVO;
import com.muyuan.system.domain.service.SysMenuDomainService;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.interfaces.dto.SysMenuDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Digits;
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

    private SysMenuDomainService sysMenuDomainService;

    @RequirePermissions("system:menu:list")
    @GetMapping("/menu/list")
    @ApiOperation(value = "菜单列表查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name",value = "菜单名称",dataType = "String",paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "状态",dataType = "String",paramType = "query")}
    )
    public Result<List<SysMenuVO>> list(@ModelAttribute SysMenuDTO sysMenuDTO) {
        List<SysMenu> list = sysMenuDomainService.list(sysMenuDTO);
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
        Optional<SysMenu> sysMenu = sysMenuDomainService.get(id);
        if (sysMenu.isPresent()) {
           return ResultUtil.success(sysMenu.get());
        }

        return ResultUtil.fail("菜单不存在");
    }

    @RequirePermissions("system:menu:delete")
    @DeleteMapping("/menu/{id}")
    @ApiOperation(value = "删除菜单")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id",value = "菜单ID",dataType = "String",paramType = "path",required = true)}
    )
    public Result<SysMenuVO> delete(@PathVariable @NotBlank(message = "菜单ID不能为空")
                                         String id) {
        sysMenuDomainService.deleteById(id);
        return ResultUtil.success();
    }

    @RequirePermissions("system:menu:add")
    @PostMapping("/menu")
    @ApiOperation("菜单添加")
    public Result add(@RequestBody @Validated SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(sysMenuDTO.getParentId());
        sysMenu.setName(sysMenuDTO.getName());
        if (GlobalConst.NOT_UNIQUE.equals(sysMenuDomainService.checkMenuNameUnique(sysMenu))) {
            return ResultUtil.fail("菜单名已存在");
        }
        if (GlobalConst.YES_FRAME.equals(sysMenuDTO.getFrame()) && StrUtil.ishttp(sysMenuDTO.getPath())) {
            return ResultUtil.fail("新增菜单'" + sysMenuDTO.getName() + "'失败，地址必须以http(s)://开头");
        }
         sysMenuDomainService.add(sysMenuDTO);

        return ResultUtil.success();
    }

    @RequirePermissions("system:menu:update")
    @PutMapping("/menu")
    @ApiOperation("菜单添加")
    public Result update(@RequestBody @Validated SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(Long.getLong(sysMenuDTO.getId()));
        sysMenu.setParentId(sysMenuDTO.getParentId());
        sysMenu.setName(sysMenuDTO.getName());
        if (GlobalConst.NOT_UNIQUE.equals(sysMenuDomainService.checkMenuNameUnique(sysMenu))) {
            return ResultUtil.fail(StrUtil.format("变更菜单名[{}]已存在",sysMenu.getName()));
        }
        if (GlobalConst.YES_FRAME.equals(sysMenuDTO.getFrame()) && StrUtil.ishttp(sysMenuDTO.getPath())) {
            return ResultUtil.fail("新增菜单[{}]失败，地址必须以http(s)://开头", sysMenuDTO.getName());
        }
        sysMenuDomainService.update(sysMenuDTO);

        return ResultUtil.success("更新成功");
    }

}
