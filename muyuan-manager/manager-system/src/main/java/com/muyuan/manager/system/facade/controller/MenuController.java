package com.muyuan.manager.system.facade.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.bean.SelectValue;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.TokenType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.redis.util.TokenUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.system.domains.model.SysMenu;
import com.muyuan.manager.system.dto.MenuQueryParams;
import com.muyuan.manager.system.dto.SysMenuDTO;
import com.muyuan.manager.system.dto.assembler.SysMenuAssembler;
import com.muyuan.manager.system.dto.vo.MenuVO;
import com.muyuan.manager.system.service.MenuService;
import com.muyuan.manager.system.dto.vo.SysMenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.*;


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
public class MenuController {

    private MenuService menuService;

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/list")
    @ApiOperation(value = "菜单列表查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name",value = "菜单名称",dataTypeClass = String.class,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "状态",dataTypeClass = String.class,paramType = "query")}
    )
    public Result<List<MenuVO>> list(@ModelAttribute MenuQueryParams params) {
        List<MenuVO> list = menuService.list(params);
        return ResultUtil.success(list);
    }

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/treeSelect")
    @ApiOperation(value = "获取菜单选择结构")
    public Result selectTree(@ModelAttribute MenuQueryParams sysMenuDTO) {
        sysMenuDTO.setStatus("0");
        List<MenuVO> list = menuService.list(sysMenuDTO);
        return ResultUtil.success(SysMenuAssembler.buildMenuVOSelectTree(list));
    }

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/roleMenuTreeSelect/{roleIds}")
    @ApiOperation(value = "获取菜单选择结构")
    public Result selectKey(@PathVariable String... roleIds) {
        List<Long> id = menuService.listSelectIdByRoleId(roleIds);
        List<MenuVO> list = menuService.list(new MenuQueryParams());
        return ResultUtil.success(new SelectValue(id,SysMenuAssembler.buildMenuVOSelectTree(list)));
    }

    @RequirePermissions("system:menu:edit")
    @GetMapping("/menu/{id}")
    @ApiOperation(value = "获取菜单详情")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id",value = "菜单ID",dataTypeClass = String.class,paramType = "path",required = true)}
    )
    public Result<SysMenuVO> get(@PathVariable String id) {
        if (StrUtil.isNumeric(id)) {
            Optional<SysMenu> sysMenu = menuService.get(id);
            if (sysMenu.isPresent()) {
                return ResultUtil.success(SysMenuAssembler.buildMenus(sysMenu.get()));
            }
        }
        return ResultUtil.fail("菜单不存在");
    }



    @RequirePermissions("system:menu:delete")
    @DeleteMapping("/menu/{id}")
    @ApiOperation(value = "删除菜单")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id",value = "菜单ID",dataTypeClass = String.class,paramType = "path",required = true)}
    )
    public Result<SysMenuVO> delete(@PathVariable @NotBlank(message = "菜单ID不能为空")
                                         String id) {
        menuService.deleteById(id);
        return ResultUtil.success();
    }


    @RequirePermissions("system:menu:add")
    @GetMapping("/menu/token")
    @ApiOperation("token生成")
    public Result token() {
        String token = TokenUtil.generate(TokenType.ADD_MENU);
        Map<String,String> res = new HashMap();
        res.put(GlobalConst.TOKEN,token);
        return ResultUtil.success(res);
    }

    @RequirePermissions("system:menu:add")
    @PostMapping("/menu")
    @ApiOperation("菜单添加")
    public Result add(@RequestBody @Validated SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(sysMenuDTO.getParentId());
        sysMenu.setName(sysMenuDTO.getName());
        if (GlobalConst.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(sysMenu))) {
            return ResultUtil.fail("菜单名已存在");
        }
        if (GlobalConst.YES_FRAME.equals(sysMenuDTO.getFrame()) && !StrUtil.ishttp(sysMenuDTO.getPath())) {
            return ResultUtil.fail("新增菜单'" + sysMenuDTO.getName() + "'失败，地址必须以http(s)://开头");
        }
         menuService.add(sysMenuDTO);

        return ResultUtil.success();
    }

    @RequirePermissions("system:menu:update")
    @PutMapping("/menu")
    @ApiOperation("菜单添加")
    public Result update(@RequestBody @Validated SysMenuDTO sysMenuDTO) {
        if (Objects.isNull(sysMenuDTO.getId())) {
            return ResultUtil.fail("id不能为空");
        }

        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(Long.valueOf(sysMenuDTO.getId()));
        sysMenu.setParentId(sysMenuDTO.getParentId());
        sysMenu.setName(sysMenuDTO.getName());
        if (GlobalConst.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(sysMenu))) {
            return ResultUtil.fail(StrUtil.format("变更菜单名[{}]已存在",sysMenu.getName()));
        }
        if (GlobalConst.YES_FRAME.equals(sysMenuDTO.getFrame()) && StrUtil.ishttp(sysMenuDTO.getPath())) {
            return ResultUtil.fail("新增菜单[{}]失败，地址必须以http(s)://开头", sysMenuDTO.getName());
        }
        menuService.update(sysMenuDTO);

        return ResultUtil.success("更新成功");
    }

}
