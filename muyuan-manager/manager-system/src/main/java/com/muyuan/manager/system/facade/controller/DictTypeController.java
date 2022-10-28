package com.muyuan.manager.system.facade.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.config.api.dto.DictTypeDTO;
import com.muyuan.config.api.dto.DictTypeRequest;
import com.muyuan.manager.system.dto.DictTypeQueryParams;
import com.muyuan.manager.system.dto.converter.DictConverter;
import com.muyuan.manager.system.dto.vo.DictTypeVO;
import com.muyuan.manager.system.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"字典类型接口"})
@AllArgsConstructor
public class DictTypeController {

    private DictTypeService dictTypeService;

    private DictConverter converter;

    @GetMapping("/dictType/list")
    @RequirePermissions("system:dict:query")
    @ApiOperation(value = "字典类型列表查询")
    public Result<Page<DictTypeDTO>> list(@ModelAttribute DictTypeQueryParams params) {
        Page<DictTypeDTO> page = dictTypeService.list(params);

        return ResultUtil.success(page);
    }

    @PostMapping("/dictType")
    @RequirePermissions("system:dict:add")
    @ApiOperation(value = "字典类型新增")
    public Result add(@RequestBody @Validated DictTypeRequest dictTypeRequest) {
        return dictTypeService.add(dictTypeRequest);
    }


    @GetMapping("/dictType/{id}")
    @ApiOperation(value = "字典类型详情查询")
    @RequirePermissions("system:dict:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "字典类型主键", dataTypeClass = String.class, paramType = "path", required = true)}
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

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/dictType/optionselect")
    @ApiOperation(value = "字典类型全列表")
    @RequirePermissions("system:dict:query")
    public Result optionselect()
    {
        List<DictTypeDTO> dictTypes = dictTypeService.selectDictTypeAll();
        return ResultUtil.success(dictTypes);
    }

}
