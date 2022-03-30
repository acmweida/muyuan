package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.system.application.vo.DictDataVO;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"字典数据接口"})
public interface DictDataController {

    @GetMapping("/dictData/{dictType}")
    @ApiOperation(value = "字典类型数值查询")
    Result<List<DictDataVO>> get(@PathVariable String dictType);

    @PostMapping("/dictData")
    @ApiOperation(value = "字典类型数新增")
    Result<List<DictDataVO>> add(@RequestBody DictDataDTO dictDataDTO);

}
