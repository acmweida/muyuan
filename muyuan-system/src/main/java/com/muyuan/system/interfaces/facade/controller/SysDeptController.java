package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.domain.model.SysDept;
import com.muyuan.system.domain.service.SysDeptDomainService;
import com.muyuan.system.interfaces.assembler.SysDeptAssembler;
import com.muyuan.system.interfaces.dto.SysDeptDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SysDeptController
 * Description 部门 Controller
 * @Author 2456910384
 * @Date 2022/5/13 10:45
 * @Version 1.0
 */
@RestController
@Api(tags = {"部门信息接口"})
@AllArgsConstructor
public class SysDeptController {

    private SysDeptDomainService sysDeptDomainService;

    @RequirePermissions("system:dept:list")
    @GetMapping("/dept/list")
    @ApiOperation(value = "部门列表查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name",value = "部门名称",dataType = "String",paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "状态",dataType = "String",paramType = "query")}
    )
    public Result<List<SysDept>> list(@ModelAttribute SysDeptDTO sysDeptDTO) {
        List<SysDept> list = sysDeptDomainService.list(sysDeptDTO);
        return ResultUtil.success(list);
    }

    @RequirePermissions("system:dept:list")
    @GetMapping("/dept/treeselect")
    @ApiOperation(value = "获取菜单选择结构")
    public Result selectTree(@ModelAttribute SysDeptDTO sysDeptDTO) {
        sysDeptDTO.setStatus("0");
        List<SysDept> list = sysDeptDomainService.list(sysDeptDTO);
        return ResultUtil.success(SysDeptAssembler.buildDeptSelectTree(list));
    }
}
