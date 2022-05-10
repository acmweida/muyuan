package com.muyuan.member.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.ExcelUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.member.application.vo.RoleVO;
import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.service.RoleDomainService;
import com.muyuan.member.interfaces.assembler.RoleAssembler;
import com.muyuan.member.interfaces.dto.RoleDTO;
import io.swagger.annotations.Api;
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

    private RoleDomainService sysRoleDomainService;

    @GetMapping("/role/list")
    @ApiOperation(value = "角色查询")
    @RequirePermissions("member:role:lise")
    public Result list(@ModelAttribute RoleDTO sysRoleDTO) {
        Page page = sysRoleDomainService.list(sysRoleDTO);
        return ResultUtil.success(page);
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "角色查询")
    @RequirePermissions("member:role:lise")
    public Result get(@PathVariable String id) {
        Optional<Role> sysRoleInfo = sysRoleDomainService.getById(id);
        if (sysRoleInfo.isPresent()) {
            return ResultUtil.success(sysRoleInfo.get());
        }

        return ResultUtil.fail("角色信息未找到");
    }

    @PostMapping("/role")
    @ApiOperation(value = "角色添加")
    @RequirePermissions("member:role:add")
    public Result add(@RequestBody @Validated RoleDTO sysRoleDTO) {
        if (GlobalConst.NOT_UNIQUE.equals(sysRoleDomainService.checkRoleCodeUnique(new Role(sysRoleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在", sysRoleDTO.getCode()));
        }

        sysRoleDomainService.add(sysRoleDTO);
        return ResultUtil.success();
    }

    @PutMapping("/role")
    @ApiOperation(value = "角色添加")
    @RequirePermissions("member:role:update")
    public Result update(@RequestBody @Validated RoleDTO sysRoleDTO) {
        if (GlobalConst.NOT_UNIQUE.equals(sysRoleDomainService.checkRoleCodeUnique(new Role(sysRoleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在", sysRoleDTO.getCode()));
        }

        sysRoleDomainService.update(sysRoleDTO);
        return ResultUtil.success();
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "角色删除")
    @RequirePermissions("member:role:del")
    public Result del(@PathVariable String... id) {
        sysRoleDomainService.deleteById(id);
        return ResultUtil.success();
    }

    @PostMapping("/role/export")
    @ApiOperation(value = "角色下载")
    @RequirePermissions("member:role:export")
    public void export(@ModelAttribute RoleDTO sysRoleDTO, HttpServletResponse response) throws IOException {
        sysRoleDTO.setEnablePage(false);
        Page<Role> page = sysRoleDomainService.list(sysRoleDTO);
        List<Role> rows = page.getRows();
        ExcelUtil.export(response, RoleVO.class, "角色信息", RoleAssembler.buildRoleVO(rows));
    }

}
