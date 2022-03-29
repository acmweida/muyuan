package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.system.application.vo.DictDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"字典数据接口"})
public interface DictDataController {

    @GetMapping("/dictType/{dictType}")
    @ApiOperation(value = "字典类型数值查询")
    Result<List<DictDataVO>> get(@PathVariable String dictType);

}
