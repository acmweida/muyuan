package com.muyuan.system.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.bean.SelectValue;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.enums.TokenType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.redis.util.TokenUtil;
import com.muyuan.common.web.annotations.Repeatable;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.dto.MenuParams;
import com.muyuan.system.dto.MenuQueryParams;
import com.muyuan.system.dto.PermissionQueryParams;
import com.muyuan.system.dto.assembler.MenuAssembler;
import com.muyuan.system.dto.converter.MenuConverter;
import com.muyuan.system.dto.vo.MenuVO;
import com.muyuan.system.service.MenuService;
import com.muyuan.system.service.PermissionService;
import com.muyuan.user.api.dto.MenuDTO;
import com.muyuan.user.api.dto.PermissionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "系统菜单接口")
@AllArgsConstructor
public class MenuController {

    private MenuService menuService;

    private MenuConverter converter;

    private PermissionService permissionService;

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/list")
    @Operation(summary = "菜单列表查询")
    @Parameters(
            {@Parameter(name = "name", description = "菜单名称", in = ParameterIn.QUERY),
                    @Parameter(name = "status", description = "状态", in = ParameterIn.QUERY)}
    )
    public Result<List<MenuVO>> list(@ModelAttribute MenuQueryParams params) {
        List<MenuDTO> list = menuService.list(params);
        return ResultUtil.success(converter.toVO(list));
    }

    @RequirePermissions("system:menu:query")
    @GetMapping("/menu/all/treeSelect/{platformType}")
    @Operation(summary = "获取菜单选择结构")
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
    @Operation(summary = "获取菜单选择结构")
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
    @Operation(summary = "获取菜单选择结构")
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
    @Operation(summary = "获取菜单详情")
    @Parameters(
            {@Parameter(name = "id", description = "菜单ID",  in = ParameterIn.PATH)}
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
    @Operation(summary = "token生成")
    public Result token() {
        String token = TokenUtil.generate(TokenType.ADD_MENU);
        Map<String, String> res = new HashMap();
        res.put(GlobalConst.TOKEN, token);
        return ResultUtil.success(res);
    }

    @RequirePermissions("system:menu:add")
    @PostMapping("/menu")
    @Operation(summary = "菜单添加")
    @Repeatable(tokenType = TokenType.ADD_MENU)
    public Result add(@RequestBody @Validated(MenuParams.Add.class) MenuParams menuParams) {
        return menuService.add(converter.toRequest(menuParams));
    }

    @RequirePermissions("system:menu:edit")
    @PutMapping("/menu")
    @Operation(summary = "菜单添加")
    public Result update(@RequestBody @Validated(MenuParams.Update.class) MenuParams menuParams) {
        if (Objects.isNull(menuParams.getId())) {
            return ResultUtil.fail("id不能为空");
        }

        return menuService.update(converter.toRequest(menuParams));
    }

    @DeleteMapping("/menu/{ids}")
    @Operation(summary = "字典类型删除")
    @RequirePermissions(value = "system:menu:remove")
    @Parameters(
            {@Parameter(name = "ids", description = "菜单主键", in = ParameterIn.PATH)}
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
