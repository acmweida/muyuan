package com.muyuan.system.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ExcelUtil;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.dto.PermissionQueryParams;
import com.muyuan.system.dto.RoleParams;
import com.muyuan.system.dto.RoleQueryParams;
import com.muyuan.system.dto.assembler.SysRoleAssembler;
import com.muyuan.system.dto.converter.PermissionConverter;
import com.muyuan.system.dto.converter.RoleConverter;
import com.muyuan.system.dto.vo.RoleVO;
import com.muyuan.system.service.PermissionService;
import com.muyuan.system.service.RoleService;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName SysRoleController
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/24 14:54
 * @Version 1.0
 */
@RestController
@Tag(name = "系统角色接口")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    private PermissionService permissionService;

    private RoleConverter converter;

    private PermissionConverter permissionConverter;

    @GetMapping("/role/list")
    @Operation(summary = "角色列表查询")
    @RequirePermissions("system:role:query")
    public Result<Page<RoleVO>> list(@ModelAttribute RoleQueryParams params) {
        Page<RoleDTO> page = roleService.list(params);
        return ResultUtil.success(Page.copy(page, converter.toVO(page.getRows())));
    }

    @GetMapping("/role/{id}")
    @Operation(summary = "角色查询")
    @RequirePermissions("system:role:query")
    @Parameters(
            {@Parameter(name = "id", description = "角色ID", in = ParameterIn.PATH)}
    )
    public Result get(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<RoleDTO> dictData = roleService.getById(id);
        List<PermissionDTO> permissions = permissionService.getByRoleId(id);

        return dictData.map(dictDataDTO -> {
            RoleVO roleVO = converter.toVO(dictDataDTO);
            roleVO.setPermissions(permissionConverter.toVO(permissions));

            return   ResultUtil.success(roleVO);
        }).orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    @Operation(summary = "角色添加用户")
    @RequirePermissions("system:role:edit")
    @PutMapping("/role/authUser/selectUserAll")
    //    @OperationSupport(includeParameters = {"id","userIds"})
    public Result selectUserAll(@RequestBody @Validated(RoleParams.SelectUser.class)  RoleParams params) {
        return roleService.selectUser(params.getId(),params.getUserIds());
    }

    @Operation(summary = "角色添加用户")
    @RequirePermissions("system:role:edit")
    @PutMapping("/role/authUser/cancelUser")
    //    @OperationSupport(includeParameters = {"id","userId"})
    public Result cancelUser(@RequestBody @Validated(RoleParams.SelectUserOne.class)  RoleParams params) {
        return  roleService.cancelUser(params.getId(),new Long[]{params.getUserId()});
    }

    @Operation(summary = "角色添加用户")
    @RequirePermissions("system:role:edit")
    @PutMapping("/role/authUser/cancelUserAll")
    //    @OperationSupport(includeParameters = {"id","userIds"})
    public Result cancelUserAll(@RequestBody @Validated(RoleParams.SelectUser.class)  RoleParams params) {
        return  roleService.cancelUser(params.getId(),params.getUserIds());
    }

    @PostMapping("/role")
    @Operation(summary = "角色添加")
    //    @OperationSupport(ignoreParameters = {"id","userIds","userId"})
    @RequirePermissions("system:role:add")
    public Result add(@RequestBody @Validated(RoleParams.Add.class) RoleParams roleParams) {
        RoleRequest request = converter.to(roleParams);
        if (ObjectUtils.isNotEmpty(roleParams.getMenuIds())) {
            Page<PermissionDTO> list = permissionService.list(PermissionQueryParams.builder()
                    .platformType(roleParams.getPlatformType())
                    .types(new String[]{GlobalConst.TYPE_DIR, GlobalConst.TYPE_MENU})
                    .build());

            List<PermissionDTO> permissionDTOS = list.getRows();
            Map<Long, Long> collect = permissionDTOS.stream().collect(Collectors.toMap(PermissionDTO::getResourceRef, PermissionDTO::getId));
            List<Long> permissionIDs = new ArrayList<>();
            for (Long menuId : roleParams.getMenuIds()) {
                permissionIDs.add(collect.get(menuId));
            }
            if (ObjectUtils.isNotEmpty(request.getPermissionIds())) {
                permissionIDs.addAll(Arrays.asList(request.getPermissionIds()));
            }
            request.setPermissionIds(permissionIDs.toArray(new Long[0]));
        }

        return roleService.add(request);
    }

    @PutMapping("/role")
    @Operation(summary = "角色添加")
    @RequirePermissions("system:role:edit")
    //    @OperationSupport(ignoreParameters = {"userIds","userId"})
    public Result update(@RequestBody @Validated(RoleParams.Update.class) RoleParams roleParams) {
        RoleRequest request = converter.to(roleParams);
        if (ObjectUtils.isNotEmpty(roleParams.getMenuIds())) {
            Page<PermissionDTO> list = permissionService.list(PermissionQueryParams.builder()
                    .platformType(roleParams.getPlatformType())
                    .types(new String[]{GlobalConst.TYPE_DIR, GlobalConst.TYPE_MENU})
                    .build());

            List<PermissionDTO> permissionDTOS = list.getRows();
            Map<Long, Long> collect = permissionDTOS.stream().collect(Collectors.toMap(PermissionDTO::getResourceRef, PermissionDTO::getId));
            List<Long> permissionIDs = new ArrayList<>();
            for (Long menuId : roleParams.getMenuIds()) {
                permissionIDs.add(collect.get(menuId));
            }
            if (ObjectUtils.isNotEmpty(request.getPermissionIds())) {
                permissionIDs.addAll(Arrays.asList(request.getPermissionIds()));
            }
            request.setPermissionIds(permissionIDs.toArray(new Long[0]));
        }

        return roleService.update(request);
    }

    @DeleteMapping("/role/{ids}")
    @Operation(summary = "角色删除")
    @RequirePermissions("system:role:remove")
    @Parameters(
            {@Parameter(name = "id", description = "角色ID", in = ParameterIn.PATH)}
    )
    public Result del(@PathVariable Long... ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                if (ObjectUtils.isEmpty(id)) {
                    return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR);
                }
            }
        }

        return roleService.deleteById(ids);
    }

    @PostMapping("/role/export")
    @Operation(summary = "角色下载")
    @RequirePermissions("system:role:export")
    @Parameters(
            {@Parameter(name = "username", description = "用户名",  in = ParameterIn.QUERY),
                    @Parameter(name = "status", description = "状态 0-正常 1-禁用",  in = ParameterIn.QUERY)}
    )
    public void export(@ModelAttribute RoleQueryParams params, HttpServletResponse response) throws IOException {
        Page<RoleDTO> page = roleService.list(params);
        List<RoleDTO> rows = page.getRows();
        ExcelUtil.export(response, RoleVO.class, "角色信息", SysRoleAssembler.buildRoleVO(rows));
    }

}
