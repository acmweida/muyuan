package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.application.vo.DictDataVO;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"字典数据接口"})
public interface DictDataController {

    @GetMapping("/dictData/list")
    @ApiOperation(value = "字典数值列表查询")
    Result<List<DictDataVO>> list(@ModelAttribute  DictDataDTO dictType);

    @GetMapping("/dictData/{dictType}")
    @ApiOperation(value = "字典类型数值查询")
    Result<List<DictDataVO>> get(@PathVariable String dictType);

    @PostMapping("/dictData")
    @ApiOperation(value = "字典类型数新增")
    Result add(@RequestBody @Validated DictDataDTO dictDataDTO);

    @DeleteMapping("/dictData/{ids}")
    @ApiOperation(value = "字典数据删除")
    @RequirePermissions(value = "system:dict:remove")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "ids",value = "字典类型主键",dataType = "String",paramType = "path",required = true)}
    )
    Result delete(@PathVariable String... ids);

}
