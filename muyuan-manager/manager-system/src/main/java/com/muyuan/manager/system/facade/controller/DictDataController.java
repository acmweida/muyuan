package com.muyuan.manager.system.facade.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.manager.system.domains.model.DictData;
import com.muyuan.manager.system.dto.DictDataQueryParams;
import com.muyuan.manager.system.dto.DictDataRequest;
import com.muyuan.manager.system.dto.assembler.DictDataAssembler;
import com.muyuan.manager.system.dto.vo.DictDataVO;
import com.muyuan.manager.system.service.DictDataDomainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"字典数据接口"})
@AllArgsConstructor
public class DictDataController {

    private DictDataDomainService dictDataDomainService;

    @GetMapping("/dictData/list")
    @ApiOperation(value = "字典数值列表查询")
    public Result<Page<DictDataDTO>> list(@ModelAttribute DictDataQueryParams params) {

        Page<DictDataDTO> page = dictDataDomainService.page(params);

        return ResultUtil.success(page);

    }

    @GetMapping("/dictData/{dictType}")
    @ApiOperation(value = "字典类型数值查询")
    public Result<List<DictDataVO>> get(@PathVariable String dictType) {
        List<DictDataVO> res = new ArrayList<>();

        if (StringUtils.isBlank(dictType)) {
            return ResultUtil.success(res);
        }

        List<DictDataDTO> dictDatas = dictDataDomainService.getByDataType(dictType);

        return ResultUtil.success(DictDataAssembler.buildDictDataVO2(dictDatas));
    }

    @GetMapping("/dictData/detail/{id}")
    @ApiOperation(value = "字典数据详情数值查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "字典数据主键", dataTypeClass = Long.class, paramType = "path", required = true)}
    )
    public Result<DictDataVO> detail(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.success();
        }

        DictDataRequest dictDataRequest = new DictDataRequest(id);
        Optional<DictData> dictData = dictDataDomainService.get(dictDataRequest);
        return ResultUtil.success(DictDataAssembler.buildDictDataVO(dictData.get()));
    }

    @PostMapping("/dictData")
    @ApiOperation(value = "字典类型数新增")
    public Result add(@RequestBody @Validated DictDataRequest dictDataRequest) {
        if (GlobalConst.NOT_UNIQUE.equals(dictDataDomainService.checkUnique(new DictData(dictDataRequest.getLabel(),
                dictDataRequest.getValue(),
                dictDataRequest.getType())))) {
            return ResultUtil.fail("已存在相同字典数据");
        }

        dictDataDomainService.add(dictDataRequest);
        return ResultUtil.success();
    }

    @PutMapping("/dictData")
    @ApiOperation(value = "字典数据更新")
    public Result update(@RequestBody @Validated DictDataRequest dictDataRequest) {
        if (ObjectUtils.isEmpty(dictDataRequest.getId())) {
            return ResultUtil.fail("id is null");
        }

        dictDataDomainService.update(dictDataRequest);
        return ResultUtil.success();
    }

    @DeleteMapping("/dictData/{ids}")
    @ApiOperation(value = "字典数据删除")
    @RequirePermissions(value = "system:dict:remove")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "ids", value = "字典数据主键", dataTypeClass = String.class, paramType = "path", required = true)}
    )
    public Result delete(@PathVariable String... ids) {
        dictDataDomainService.deleteById(ids);
        return ResultUtil.success();
    }

}
