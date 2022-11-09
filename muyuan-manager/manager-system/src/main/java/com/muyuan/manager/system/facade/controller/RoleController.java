package com.muyuan.manager.system.facade.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ExcelUtil;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.system.domains.model.SysRole;
import com.muyuan.manager.system.dto.RoleQueryParams;
import com.muyuan.manager.system.dto.SysRoleDTO;
import com.muyuan.manager.system.dto.SysUserDTO;
import com.muyuan.manager.system.dto.assembler.SysRoleAssembler;
import com.muyuan.manager.system.dto.converter.RoleConverter;
import com.muyuan.manager.system.dto.vo.RoleVO;
import com.muyuan.manager.system.service.RoleService;
import com.muyuan.manager.system.service.SysUserDomainService;
import com.muyuan.user.api.dto.RoleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName SysRoleController
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/24 14:54
 * @Version 1.0
 */
@RestController
@Api(tags = {"系统角色接口"})
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    private RoleConverter converter;

    private SysUserDomainService sysUserDomainService;

    @GetMapping("/role/list")
    @ApiOperation(value = "角色列表查询")
    @RequirePermissions("system:role:query")
    public Result<Page<RoleDTO>> list(@ModelAttribute RoleQueryParams params) {
        Page page = roleService.list(params);
        return ResultUtil.success(page);
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "角色查询")
    @RequirePermissions("system:role:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "角色ID", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result get(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<RoleDTO> dictData = roleService.getById(id);

        return dictData.map(dictDataDTO -> ResultUtil.success(converter.toVO(dictDataDTO))).orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    @ApiOperation(value = "角色分配用户查询")
    @RequirePermissions("system:role:query")
    @GetMapping("/role/authUser/allocatedList")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "roleId", value = "角色ID", dataTypeClass = Long.class, paramType = "query", required = true),
                    @ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "phone", value = "手机号", dataTypeClass = String.class, paramType = "query")
            }
    )
    public Result allocatedList(@ModelAttribute SysUserDTO sysUserDTO) {
        return ResultUtil.success(sysUserDomainService.selectAllocatedList(sysUserDTO));
    }

    @ApiOperation(value = "角色为分配用户查询")
    @RequirePermissions("system:role:query")
    @GetMapping("/role/authUser/unallocatedList")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "roleId", value = "角色ID", dataTypeClass = Long.class, paramType = "query", required = true),
                    @ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "phone", value = "手机号", dataTypeClass = String.class, paramType = "query")
            }
    )
    public Result unallocatedList(@ModelAttribute SysUserDTO sysUserDTO) {
        return ResultUtil.success(sysUserDomainService.selectUnallocatedList(sysUserDTO));
    }

    @ApiOperation(value = "角色添加用户")
    @RequirePermissions("system:role:edit")
    @PutMapping("/role/authUser/selectAll")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "roleId", value = "角色ID", dataTypeClass = Long.class, paramType = "body", required = true),
                    @ApiImplicitParam(name = "userIds", value = "用户ID", dataType = "Long{]",dataTypeClass = Long.class,paramType = "body",required = true)
            }
    )
    @Valid
    public Result selectAll( @NotNull(message = "角色ID必填") Long roleId,
                             @NotBlank(message = "用户ID列表不能为空") Long[] userIds) {
        roleService.selectUser(roleId,userIds);
        return ResultUtil.success();
    }

    @PostMapping("/role")
    @ApiOperation(value = "角色添加")
    @RequirePermissions("system:role:add")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "code", value = "角色编码", dataTypeClass = Long.class, paramType = "body", required = true),
                    @ApiImplicitParam(name = "name", value = "角色名称", dataTypeClass = String.class, paramType = "body", required = true),
                    @ApiImplicitParam(name = "menuIds", value = "权限菜单ID", dataType = "Long[]",dataTypeClass = Long.class,paramType = "body"),
                    @ApiImplicitParam(name = "status", value = "状态 0-正常 1-禁用", dataTypeClass = String.class, paramType = "body")
            }
    )
    public Result add(@RequestBody @Valid SysRoleDTO sysRoleDTO) {

        if (GlobalConst.NOT_UNIQUE.equals(roleService.checkRoleCodeUnique(new SysRole(sysRoleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在", sysRoleDTO.getCode()));
        }

        roleService.add(sysRoleDTO);


        return ResultUtil.success();
    }

    @PutMapping("/role")
    @ApiOperation(value = "角色添加")
    @RequirePermissions("system:role:update")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "角色ID", dataTypeClass = Long.class, paramType = "body", required = true),
                    @ApiImplicitParam(name = "code", value = "角色编码", dataTypeClass = Long.class, paramType = "body", required = true),
                    @ApiImplicitParam(name = "name", value = "角色名称", dataTypeClass = String.class, paramType = "body", required = true),
                    @ApiImplicitParam(name = "menuIds", value = "权限菜单ID", dataType = "Long[]",dataTypeClass = Long.class, paramType = "body"),
                    @ApiImplicitParam(name = "status", value = "状态 0-正常 1-禁用", dataTypeClass = String.class, paramType = "body")
            }
    )
    public Result update(@RequestBody @Validated SysRoleDTO sysRoleDTO) {
        if (GlobalConst.NOT_UNIQUE.equals(roleService.checkRoleCodeUnique(new SysRole(sysRoleDTO.getId(), sysRoleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在", sysRoleDTO.getCode()));
        }

        roleService.update(sysRoleDTO);

        return ResultUtil.success();
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "角色删除")
    @RequirePermissions("system:role:del")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "角色ID", dataType = "Long[]",dataTypeClass = Long.class, paramType = "path", required = true)}
    )
    public Result del(@PathVariable String... id) {
        roleService.deleteById(id);
        return ResultUtil.success();
    }

    @PostMapping("/role/export")
    @ApiOperation(value = "角色下载")
    @RequirePermissions("system:role:export")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "status", value = "状态 0-正常 1-禁用", dataTypeClass = String.class, paramType = "query")}
    )
    public void export(@ModelAttribute RoleQueryParams params, HttpServletResponse response) throws IOException {
        Page<RoleDTO> page = roleService.list(params);
        List<RoleDTO> rows = page.getRows();
        ExcelUtil.export(response, RoleVO.class, "角色信息", SysRoleAssembler.buildRoleVO(rows));
    }

}
