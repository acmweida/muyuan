package com.muyuan.system.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.dto.DeptParams;
import com.muyuan.system.dto.DeptQueryParams;
import com.muyuan.system.dto.assembler.DeptAssembler;
import com.muyuan.system.dto.converter.DeptConverter;
import com.muyuan.system.service.DeptService;
import com.muyuan.user.api.dto.DeptDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DeptController
 * Description 部门 Controller
 * @Author 2456910384
 * @Date 2022/5/13 10:45
 * @Version 1.0
 */
@RestController
@Tag(name = "部门信息接口")
@AllArgsConstructor
public class DeptController {

    private DeptService deptService;

    private DeptConverter converter;

    @RequirePermissions("system:dept:query")
    @GetMapping("/dept/list")
    @Operation(description = "部门列表查询")
    public Result<List<DeptDTO>> list(@ModelAttribute DeptQueryParams params) {
        List<DeptDTO> list = deptService.list(params);
        return ResultUtil.success(list);
    }

    @RequirePermissions("system:dept:query")
    @GetMapping("/dept/treeselect")
    @Operation(description = "获取菜单选择结构")
    public Result selectTree(@ModelAttribute DeptQueryParams deptQueryParams) {
        deptQueryParams.setStatus("0");
        List<DeptDTO> list = deptService.list(deptQueryParams);
        return ResultUtil.success(DeptAssembler.buildDeptSelectTree(list));
    }

    @RequirePermissions("system:dept:add")
    @PostMapping("/dept")
    @Operation(description = "新增部门")
    public Result add(@RequestBody @Validated DeptParams params) {
        return deptService.add(converter.toRequest(params));
    }
}
