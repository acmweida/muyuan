package com.store.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.ExcelUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.store.system.domains.vo.RoleVO;
import com.store.system.domains.model.Role;
import com.store.system.domains.service.RoleDomainService;
import com.store.system.interfaces.assembler.RoleAssembler;
import com.store.system.domains.dto.RoleDTO;
import com.store.system.domains.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
@Api(tags = {"角色接口"})
@AllArgsConstructor
public class RoleController {

    private RoleDomainService roleDomainService;

    @GetMapping("/role/list")
    @ApiOperation(value = "角色查询")
    @RequirePermissions("member:role:lise")
    public Result list(@ModelAttribute RoleDTO sysRoleDTO) {
        Page page = roleDomainService.page(sysRoleDTO);
        return ResultUtil.success(page);
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "角色查询")
    @RequirePermissions("member:role:lise")
    public Result get(@PathVariable Long id) {
        Optional<Role> sysRoleInfo = roleDomainService.getById(id);
        if (sysRoleInfo.isPresent()) {
            return ResultUtil.success(sysRoleInfo.get());
        }

        return ResultUtil.fail("角色信息未找到");
    }

    @PostMapping("/role")
    @ApiOperation(value = "角色添加")
    @RequirePermissions("member:role:add")
    public Result add(@RequestBody @Validated RoleDTO roleDTO) {
        if (GlobalConst.NOT_UNIQUE.equals(roleDomainService.checkRoleCodeUnique(new Role(roleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在", roleDTO.getCode()));
        }

        roleDomainService.add(roleDTO);
        return ResultUtil.success();
    }

    @PutMapping("/role")
    @ApiOperation(value = "角色添加")
    @RequirePermissions("member:role:update")
    public Result update(@RequestBody @Validated RoleDTO roleDTO) {
        if (GlobalConst.NOT_UNIQUE.equals(roleDomainService.checkRoleCodeUnique(new Role(roleDTO.getId(),roleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在", roleDTO.getCode()));
        }

        roleDomainService.update(roleDTO);
        return ResultUtil.success();
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "角色删除")
    @RequirePermissions("member:role:del")
    public Result del(@PathVariable String... id) {
        roleDomainService.deleteById(id);
        return ResultUtil.success();
    }

    @PostMapping("/role/export")
    @ApiOperation(value = "角色下载")
    @RequirePermissions("member:role:export")
    public void export(@ModelAttribute RoleDTO sysRoleDTO, HttpServletResponse response) throws IOException {
        List<Role> rows = roleDomainService.list(sysRoleDTO);
        ExcelUtil.export(response, RoleVO.class, "角色信息", RoleAssembler.buildRoleVO(rows));
    }

    @ApiOperation(value = "角色分配用户查询")
    @RequirePermissions("member:role:list")
    @GetMapping("/role/authUser/allocatedList")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "roleId", value = "角色ID", dataTypeClass = Long.class, paramType = "query", required = true),
                    @ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "phone", value = "手机号", dataTypeClass = String.class, paramType = "query")
            }
    )
    public Result allocatedList(@ModelAttribute UserDTO userDTO) {
        return ResultUtil.success(roleDomainService.selectAllocatedList(userDTO));
    }

}
