package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.application.service.DictDataService;
import com.muyuan.system.application.vo.DictDataVO;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.interfaces.assembler.DictDataAssembler;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = {"字典数据接口"})
@AllArgsConstructor
public class DictDataController {

    private DictDataService dictDataService;

    @GetMapping("/dictData/list")
    @ApiOperation(value = "字典数值列表查询")
    public Result<List<DictDataVO>> list(@ModelAttribute  DictDataDTO dictDataDTO) {

        Page page  = dictDataService.list(dictDataDTO);
        List<DictData> list  = page.getRows();

        page.setRows(DictDataAssembler.buildDictDataVO(list));

        return ResultUtil.success(page);

    }

    @GetMapping("/dictData/{dictType}")
    @ApiOperation(value = "字典类型数值查询")
    public Result<List<DictDataVO>> get(@PathVariable String dictType) {
        List<DictDataVO> res = new ArrayList<>();

        if (StringUtils.isBlank(dictType)) {
            return ResultUtil.success(res);
        }

        List<DictData> dictDatas = dictDataService.getByDataType(dictType);

        return ResultUtil.success(DictDataAssembler.buildDictDataVO(dictDatas));
    }

    @PostMapping("/dictData")
    @ApiOperation(value = "字典类型数新增")
   public Result add(@RequestBody @Validated DictDataDTO dictDataDTO) {
        Integer res = dictDataService.add(dictDataDTO);
        if (res == 0) {
            return ResultUtil.success();
        } else if (res == 1) {
            return ResultUtil.fail("已存在相同字典数据");
        }
        return ResultUtil.fail();
    }

    @DeleteMapping("/dictData/{ids}")
    @ApiOperation(value = "字典数据删除")
    @RequirePermissions(value = "system:dict:remove")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "ids",value = "字典类型主键",dataType = "String",paramType = "path",required = true)}
    )
    public Result delete(@PathVariable String... ids) {
        if (dictDataService.deleteById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }

}
