package com.muyuan.store.system.interfaces.facade.controller;

import com.muyuan.common.core.bean.SelectValue;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.TokenType;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.redis.util.TokenUtil;
import com.muyuan.common.web.annotations.Repeatable;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.store.system.domains.dto.MenuDTO;
import com.muyuan.store.system.domains.model.Menu;
import com.muyuan.store.system.domains.service.MenuDomainService;
import com.muyuan.store.system.domains.vo.MenuVO;
import com.muyuan.store.system.domains.vo.RouterVo;
import com.muyuan.store.system.interfaces.assembler.MenuAssembler;
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

    private MenuDomainService menuDomainService;

    @GetMapping("/menu/route")
    @ApiOperation(value = "路由信息获取")
    Result<List<RouterVo>> getRouter() {
        List<String> roles = SecurityUtils.getRoles();
        List<Menu> menus = menuDomainService.selectMenuByRoleCodes(roles);
        return ResultUtil.success(MenuAssembler.buildMenus(MenuAssembler.buildMenuTree(menus)));
    }

    @RequirePermissions("member:menu:list")
    @GetMapping("/menu/list")
    @ApiOperation(value = "菜单列表查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name",value = "菜单名称",dataTypeClass = String.class,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "状态",dataTypeClass = String.class,paramType = "query")}
    )
    public Result<List<MenuVO>> list(@ModelAttribute MenuDTO menuDTO) {
        List<Menu> list = menuDomainService.list(menuDTO);
        return ResultUtil.success(list);
    }

    @RequirePermissions("member:menu:edit")
    @GetMapping("/menu/treeSelect")
    @ApiOperation(value = "获取菜单选择结构")
    public Result selectTree(@ModelAttribute MenuDTO sysMenuDTO) {
        sysMenuDTO.setStatus("0");
        List<Menu> list = menuDomainService.list(sysMenuDTO);
        return ResultUtil.success(MenuAssembler.buildMenuSelectTree(list));
    }

    @RequirePermissions("member:menu:edit")
    @GetMapping("/menu/roleMenuTreeSelect/{roleIds}")
    @ApiOperation(value = "获取菜单选择结构")
    public Result selectKey(@PathVariable String... roleIds) {
        List<Long> id = menuDomainService.listSelectIdByRoleId(roleIds);
        List<Menu> list = menuDomainService.list(new MenuDTO());
        return ResultUtil.success(new SelectValue(id,MenuAssembler.buildMenuSelectTree(list)));
    }

    @RequirePermissions("member:menu:edit")
    @GetMapping("/menu/{id}")
    @ApiOperation(value = "获取菜单详情")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id",value = "菜单ID",dataTypeClass = String.class,paramType = "path",required = true)}
    )
    public Result<MenuVO> get(@PathVariable String id) {
        if (StrUtil.isNumeric(id)) {
            Optional<Menu> sysMenu = menuDomainService.get(id);
            if (sysMenu.isPresent()) {
                return ResultUtil.success(sysMenu.get());
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
    public Result<MenuVO> delete(@PathVariable @NotBlank(message = "菜单ID不能为空")
                                         String id) {
        menuDomainService.deleteById(id);
        return ResultUtil.success();
    }

    @RequirePermissions("system:menu:add")
    @PostMapping("/menu")
    @ApiOperation("菜单添加")
    @Repeatable(tokenType = TokenType.ADD_MENU)
    public Result add(@RequestBody @Validated MenuDTO sysMenuDTO ,@RequestParam String token) {
        Menu sysMenu = new Menu();
        sysMenu.setParentId(sysMenuDTO.getParentId());
        sysMenu.setName(sysMenuDTO.getName());
        if (GlobalConst.NOT_UNIQUE.equals(menuDomainService.checkMenuNameUnique(sysMenu))) {
            return ResultUtil.fail("菜单名已存在");
        }
        if (GlobalConst.YES_FRAME.equals(sysMenuDTO.getFrame()) && !StrUtil.ishttp(sysMenuDTO.getPath())) {
            return ResultUtil.fail("新增菜单'" + sysMenuDTO.getName() + "'失败，地址必须以http(s)://开头");
        }
        menuDomainService.add(sysMenuDTO);

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

    @RequirePermissions("system:menu:update")
    @PutMapping("/menu")
    @ApiOperation("菜单添加")
    public Result update(@RequestBody @Validated MenuDTO menuDTO) {
        if (Objects.isNull(menuDTO.getId())) {
            return ResultUtil.fail("id不能为空");
        }

        Menu menu = new Menu();
        menu.setId(Long.valueOf(menuDTO.getId()));
        menu.setParentId(menuDTO.getParentId());
        menu.setName(menuDTO.getName());
        menu.setType(menuDTO.getType());
        if (GlobalConst.NOT_UNIQUE.equals(menuDomainService.checkMenuNameUnique(menu))) {
            return ResultUtil.fail(StrUtil.format("变更菜单名[{}]已存在",menu.getName()));
        }
        if (GlobalConst.YES_FRAME.equals(menuDTO.getFrame()) && StrUtil.ishttp(menuDTO.getPath())) {
            return ResultUtil.fail("新增菜单[{}]失败，地址必须以http(s)://开头", menuDTO.getName());
        }
        menuDomainService.update(menuDTO);

        return ResultUtil.success("更新成功");
    }

}
