package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.service.SysRoleDomainService;
import com.muyuan.system.interfaces.dto.SysRoleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/role/list")
    @ApiOperation(value = "角色查询")
    @RequirePermissions("system:role:lise")
    public Result list(@ModelAttribute SysRoleDTO sysRoleDTO) {
        Page page = sysRoleDomainService.list(sysRoleDTO);
        return ResultUtil.success(page);
    }

    @PutMapping("/role")
    @ApiOperation(value = "角色添加")
    @RequirePermissions("system:role:add")
    public  Result add(@RequestBody @Validated SysRoleDTO sysRoleDTO) {

        if (GlobalConst.NOT_UNIQUE.equals(sysRoleDomainService.checkRoleCodeUnique(new SysRole(sysRoleDTO.getCode())))) {
            return ResultUtil.fail(StrUtil.format("角色编码:{}已存在",sysRoleDTO.getCode()));
        }

        sysRoleDomainService.add(sysRoleDTO);


        return ResultUtil.success();
    }
}
