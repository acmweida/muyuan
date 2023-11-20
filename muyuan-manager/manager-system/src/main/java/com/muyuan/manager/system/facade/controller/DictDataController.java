package com.muyuan.manager.system.facade.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.manager.system.dto.DictDataQueryParams;
import com.muyuan.manager.system.dto.DictDataParams;
import com.muyuan.manager.system.dto.converter.DictConverter;
import com.muyuan.manager.system.dto.vo.DictDataVO;
import com.muyuan.manager.system.service.DictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Api(tags = {"字典数据接口"})
@AllArgsConstructor
public class DictDataController {

    private DictDataService dictDataService;

    private DictConverter converter;

    @GetMapping("/dictData/list")
    @ApiOperation(value = "字典数值列表查询")
    @RequirePermissions(value = "system:dict:query")
    public Result<Page<DictDataDTO>> list(@ModelAttribute DictDataQueryParams params) {

        Page<DictDataDTO> page = dictDataService.page(params);

        return ResultUtil.success(page);

    }

    @GetMapping("/dictData/detail/{id}")
    @ApiOperation(value = "字典数据详情数值查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "字典数据主键", dataTypeClass = Long.class, paramType = "path", required = true)}
    )
    public Result<DictDataVO> detail(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<DictDataDTO> dictData = dictDataService.getById(id);

        return dictData.map(dictDataDTO -> ResultUtil.success(converter.toVO(dictDataDTO))).orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }


    @PutMapping("/dictData")
    @ApiOperation(value = "字典数据更新")
    @RequirePermissions(value = "system:dict:edit")
    public Result update(@RequestBody @Validated(DictDataParams.Update.class) DictDataParams dictDataParams) {
        if (ObjectUtils.isEmpty(dictDataParams.getId())) {
            return ResultUtil.fail("id is null");
        }

        return dictDataService.update(dictDataParams);
    }

    @DeleteMapping("/dictData/{ids}")
    @ApiOperation(value = "字典数据删除")
    @RequirePermissions(value = "system:dict:remove")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "ids", value = "字典数据主键", dataTypeClass = String.class, paramType = "path", required = true)}
    )
    public Result delete(@PathVariable String... ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                if (!StringUtils.isNumeric(id)) {
                    return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR);
                }
            }
        }

        return dictDataService.deleteById(Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList()).toArray(new Long[0]));
    }


    @PostMapping("/dictData")
    @ApiOperation(value = "字典类型数新增")
    //    //    @ApiOperationSupport(ignoreParameters = "id")
    @RequirePermissions(value = "system:dict:add")
    public Result add(@RequestBody @Validated(DictDataParams.Add.class) DictDataParams dictDataParams) {
        return dictDataService.add(dictDataParams);
    }


}
