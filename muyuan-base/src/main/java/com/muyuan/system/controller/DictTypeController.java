package com.muyuan.system.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.config.api.dto.DictTypeDTO;
import com.muyuan.system.dto.DictTypeParams;
import com.muyuan.system.dto.DictTypeQueryParams;
import com.muyuan.system.dto.converter.DictConverter;
import com.muyuan.system.dto.vo.DictTypeVO;
import com.muyuan.system.service.DictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name = "字典类型接口")
@AllArgsConstructor
public class DictTypeController {

    private DictTypeService dictTypeService;

    private DictConverter converter;

    @GetMapping("/dictType/list")
    @RequirePermissions("system:dict:query")
    @Operation(summary = "字典类型列表查询")
    public Result<Page<DictTypeDTO>> list(@ModelAttribute DictTypeQueryParams params) {
        Page<DictTypeDTO> page = dictTypeService.list(params);

        return ResultUtil.success(page);
    }

    @PostMapping("/dictType")
    @RequirePermissions("system:dict:add")
    @Operation(summary = "字典类型新增")
    //    //    @OperationSupport(ignoreParameters = "id")
    public Result add(@RequestBody @Validated DictTypeParams dictTypeParams) {
        return dictTypeService.add(dictTypeParams);
    }


    @GetMapping("/dictType/{id}")
    @Operation(summary = "字典类型详情查询")
    @RequirePermissions("system:dict:query")
    @Parameters(
            {@Parameter(name = "id", description = "字典类型主键", in = ParameterIn.PATH)}
    )
    public Result<DictTypeVO> getById(@PathVariable  String id) {
        if (StrUtil.isNumeric(id)) {
            Optional<DictTypeDTO> dictType = dictTypeService.getById(Long.valueOf(id));
            if (dictType.isPresent()) {
                return ResultUtil.success(converter.toVO(dictType.get()));
            }
        }
        return ResultUtil.fail("字典类型未找到");
    }


    @PutMapping("/dictType")
    @Operation(description = "字典类型更新")
    @RequirePermissions(value = "system:dict:edit")
    public Result update(@RequestBody @Validated(DictTypeParams.Update.class) DictTypeParams dictTypeParams) {
        if (ObjectUtils.isEmpty(dictTypeParams.getId())) {
            return ResultUtil.fail("id is null");
        }

        return dictTypeService.update(dictTypeParams);
    }

    @DeleteMapping("/dictType/{ids}")
    @Operation(summary = "字典类型删除")
    @RequirePermissions(value = "system:dict:remove")
    @Parameters(
            {@Parameter(name = "ids", description = "字典类型主键", in = ParameterIn.PATH)}
    )
    public Result delete(@PathVariable @Validated @NotNull(message = "ids 不能为空") String... ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                if (!StringUtils.isNumeric(id)) {
                    return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR);
                }
            }
        }

        return dictTypeService.deleteById(Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList()).toArray(new Long[0]));
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/dictType/optionselect")
    @Operation(summary = "字典类型全列表")
    @RequirePermissions("system:dict:query")
    public Result optionselect()
    {
        List<DictTypeDTO> dictTypes = dictTypeService.selectDictTypeAll();
        return ResultUtil.success(dictTypes);
    }

}
