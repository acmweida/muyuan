package com.muyuan.manager.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.ExcelUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.system.interfaces.assembler.SysRoleAssembler;
import com.muyuan.manager.system.domains.vo.SysRoleVO;
import com.muyuan.manager.system.domains.model.SysRole;
import com.muyuan.manager.system.domains.service.SysRoleDomainService;
import com.muyuan.manager.system.domains.service.SysUserDomainService;
import com.muyuan.manager.system.domains.dto.SysRoleDTO;
import com.muyuan.manager.system.domains.dto.SysUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
public class SysRoleController {

    private SysRoleDomainService sysRoleDomainService;

    private SysUserDomainService sysUserDomainService;

    @GetMapping("/role/list")
    @ApiOperation(value = "角色查询")
    @RequirePermissions("system:role:list")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "status", value = "状态 0-正常 1-禁用", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "pageSize", value = "页数", dataTypeClass = String.class, paramType = "query")
            }
    )
    public Result list(@ModelAttribute SysRoleDTO sysRoleDTO) {
        Page page = sysRoleDomainService.list(sysRoleDTO);
        return ResultUtil.success(page);
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "角色查询")
    @RequirePermissions("system:role:list")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "角色ID", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result get(@PathVariable String id) {
        Optional<SysRole> sysRoleInfo = sysRoleDomainService.getById(id);
        if (sysRoleInfo.isPresent()) {
            return ResultUtil.success(sysRoleInfo.get());
        }

        return ResultUtil.fail("角色信息未找到");
    }

    @ApiOperation(value = "角色分配用户查询")
    @RequirePermissions("system:role:list")
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
    @RequirePermissions("system:role:list")
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
        sysRoleDomainService.selectUser(roleId,userIds);
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

        if (GlobalConst.NOT_UNIQUE.equals(sysRoleDomainService.checkRoleCodeUnique(new SysRole(sysRoleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在", sysRoleDTO.getCode()));
        }

        sysRoleDomainService.add(sysRoleDTO);


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
        if (GlobalConst.NOT_UNIQUE.equals(sysRoleDomainService.checkRoleCodeUnique(new SysRole(sysRoleDTO.getId(), sysRoleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在", sysRoleDTO.getCode()));
        }

        sysRoleDomainService.update(sysRoleDTO);

        return ResultUtil.success();
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "角色删除")
    @RequirePermissions("system:role:del")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "角色ID", dataType = "Long[]",dataTypeClass = Long.class, paramType = "path", required = true)}
    )
    public Result del(@PathVariable String... id) {
        sysRoleDomainService.deleteById(id);
        return ResultUtil.success();
    }

    @PostMapping("/role/export")
    @ApiOperation(value = "角色下载")
    @RequirePermissions("system:role:export")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "status", value = "状态 0-正常 1-禁用", dataTypeClass = String.class, paramType = "query")}
    )
    public void export(@ModelAttribute SysRoleDTO sysRoleDTO, HttpServletResponse response) throws IOException {
        sysRoleDTO.setEnablePage(false);
        Page<SysRole> page = sysRoleDomainService.list(sysRoleDTO);
        List<SysRole> rows = page.getRows();
        ExcelUtil.export(response, SysRoleVO.class, "角色信息", SysRoleAssembler.buildSysRoleVO(rows));
    }

}
