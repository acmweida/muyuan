package com.muyuan.system.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.system.dto.DictDataParams;
import com.muyuan.system.dto.DictDataQueryParams;
import com.muyuan.system.dto.converter.DictConverter;
import com.muyuan.system.dto.vo.DictDataVO;
import com.muyuan.system.service.DictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name = "字典数据接口")
@AllArgsConstructor
public class DictDataController {

    private DictDataService dictDataService;

    private DictConverter converter;

    @GetMapping("/dictData/list")
    @Operation(description = "字典数值列表查询")
    @RequirePermissions(value = "system:dict:query")
    public Result<Page<DictDataDTO>> list(@ModelAttribute DictDataQueryParams params) {

        Page<DictDataDTO> page = dictDataService.page(params);

        return ResultUtil.success(page);

    }

    @GetMapping("/dictData/detail/{id}")
    @Operation(description = "字典数据详情数值查询")
    @Parameters(
            {@Parameter(name = "id", description = "字典数据主键",  in = ParameterIn.PATH)}
    )
    public Result<DictDataVO> detail(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<DictDataDTO> dictData = dictDataService.getById(id);

        return dictData.map(dictDataDTO -> ResultUtil.success(converter.toVO(dictDataDTO))).orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }


    @PutMapping("/dictData")
    @Operation(description = "字典数据更新")
    @RequirePermissions(value = "system:dict:edit")
    public Result update(@RequestBody @Validated(DictDataParams.Update.class) DictDataParams dictDataParams) {
        if (ObjectUtils.isEmpty(dictDataParams.getId())) {
            return ResultUtil.fail("id is null");
        }

        return dictDataService.update(dictDataParams);
    }

    @DeleteMapping("/dictData/{ids}")
    @Operation(description = "字典数据删除")
    @RequirePermissions(value = "system:dict:remove")
    @Parameters(
            {@Parameter(name = "ids", description = "字典数据主键", in = ParameterIn.PATH)}
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
    @Operation(description = "字典类型数新增")
    //    //    @OperationSupport(ignoreParameters = "id")
    @RequirePermissions(value = "system:dict:add")
    public Result add(@RequestBody @Validated(DictDataParams.Add.class) DictDataParams dictDataParams) {
        return dictDataService.add(dictDataParams);
    }


}
