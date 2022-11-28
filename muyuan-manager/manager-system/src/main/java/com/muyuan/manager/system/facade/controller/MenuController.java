package com.muyuan.manager.system.facade.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.bean.SelectValue;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.enums.TokenType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.redis.util.TokenUtil;
import com.muyuan.common.web.annotations.Repeatable;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.system.dto.MenuParams;
import com.muyuan.manager.system.dto.MenuQueryParams;
import com.muyuan.manager.system.dto.PermissionQueryParams;
import com.muyuan.manager.system.dto.assembler.MenuAssembler;
import com.muyuan.manager.system.dto.converter.MenuConverter;
import com.muyuan.manager.system.dto.vo.MenuVO;
import com.muyuan.manager.system.service.MenuService;
import com.muyuan.manager.system.service.PermissionService;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.PermissionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;


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

    private MenuConverter converter;

    private PermissionService permissionService;

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/list")
    @ApiOperation(value = "菜单列表查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name", value = "菜单名称", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "status", value = "状态", dataTypeClass = String.class, paramType = "query")}
    )
    public Result<List<MenuVO>> list(@ModelAttribute MenuQueryParams params) {
        List<MenuDTO> list = menuService.list(params);
        return ResultUtil.success(converter.toVO(list));
    }

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/all/treeSelect/{platformType}")
    @ApiOperation(value = "获取菜单选择结构")
    public Result selectTreeAll(@PathVariable Integer platformType) {
        List<MenuDTO> list = menuService.list(MenuQueryParams.builder()
                .platformType(platformType)
                .status(GlobalConst.TRUE)
                .build());
        return ResultUtil.success(MenuAssembler.buildMenuVOSelectTree(converter.toVO(
                list)
        ));
    }

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/treeSelect/{platformType}")
    @ApiOperation(value = "获取菜单选择结构")
    public Result selectTree(@PathVariable Integer platformType) {
        List<MenuDTO> list = menuService.list(MenuQueryParams.builder()
                .platformType(platformType)
                .status(GlobalConst.TRUE)
                .build());
        List<Long> menuIds = permissionService.list(PermissionQueryParams.builder()
                .platformType(platformType)
                .build()).getRows().stream().map(PermissionDTO::getResourceRef).filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        return ResultUtil.success(MenuAssembler.buildMenuVOSelectTree(converter.toVO(
                list.stream().filter(item -> menuIds.contains(item.getId())).collect(Collectors.toList())
        )));
    }

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/roleMenuTreeSelect/{platformType}/{roleId}")
    @ApiOperation(value = "获取菜单选择结构")
    public Result selectKey(@PathVariable Long roleId,@PathVariable Integer platformType) {
        List<Long> id = menuService.listByRoleId(roleId).stream().map(MenuDTO::getId).collect(Collectors.toList());
        List<MenuDTO> list = menuService.list(MenuQueryParams.builder()
                .platformType(platformType)
                .status(GlobalConst.TRUE)
                .build());
        List<Long> menuIds = permissionService.list(PermissionQueryParams.builder()
                .platformType(platformType)
                .build()).getRows().stream().map(PermissionDTO::getResourceRef).filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());

        return ResultUtil.success(new SelectValue(id, MenuAssembler.buildMenuVOSelectTree(
                converter.toVO(list.stream().filter(item -> menuIds.contains(item.getId())).collect(Collectors.toList())))
        ));
    }

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/{id}")
    @ApiOperation(value = "获取菜单详情")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "菜单ID", dataTypeClass = String.class, paramType = "path", required = true)}
    )
    public Result<MenuVO> get(@PathVariable @Validated @NotNull Long id) {
        if (ObjectUtils.isNotEmpty(id)) {
            Optional<MenuDTO> menuDTO = menuService.get(id);
            return menuDTO.map(menuDTO_ -> ResultUtil.success(converter.toVO(menuDTO_)))
                    .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
        }
        return ResultUtil.fail("菜单不存在");
    }

    @RequirePermissions("system:menu:add")
    @GetMapping("/menu/token")
    @ApiOperation("token生成")
    public Result token() {
        String token = TokenUtil.generate(TokenType.ADD_MENU);
        Map<String, String> res = new HashMap();
        res.put(GlobalConst.TOKEN, token);
        return ResultUtil.success(res);
    }

    @RequirePermissions("system:menu:add")
    @PostMapping("/menu")
    @ApiOperation("菜单添加")
    @Repeatable(tokenType = TokenType.ADD_MENU)
    public Result add(@RequestBody @Validated(MenuParams.Add.class) MenuParams menuParams) {
        return menuService.add(converter.toRequest(menuParams));
    }

    @RequirePermissions("system:menu:edit")
    @PutMapping("/menu")
    @ApiOperation("菜单添加")
    public Result update(@RequestBody @Validated(MenuParams.Update.class) MenuParams menuParams) {
        if (Objects.isNull(menuParams.getId())) {
            return ResultUtil.fail("id不能为空");
        }

        return menuService.update(converter.toRequest(menuParams));
    }

    @DeleteMapping("/menu/{ids}")
    @ApiOperation(value = "字典类型删除")
    @RequirePermissions(value = "system:menu:remove")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "ids", value = "菜单主键", dataTypeClass = String.class, paramType = "path", required = true)}
    )
    public Result delete(@PathVariable @Validated @NotNull(message = "ids 不能为空") String... ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                if (!StringUtils.isNumeric(id)) {
                    return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR);
                }
            }
        }

        return menuService.deleteById(Arrays.stream(ids).map(Long::parseLong).toArray(Long[]::new));
    }

}
