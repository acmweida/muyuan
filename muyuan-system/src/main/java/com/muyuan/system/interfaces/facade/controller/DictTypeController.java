package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.application.service.DictTypeService;
import com.muyuan.system.application.vo.DictTypeVO;
import com.muyuan.system.interfaces.assembler.DictTypeAssembler;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"字典类型接口"})
@AllArgsConstructor
public class DictTypeController {

    private DictTypeService dictTypeService;

    @GetMapping("/dictType/list")
    @ApiOperation(value = "字典类型列表查询")
    public Result<List<DictTypeVO>> list(@ModelAttribute DictTypeDTO dictTypeDTO) {
        Page page  = dictTypeService.list(dictTypeDTO);
        List<DictType> list  = page.getRows();

        page.setRows(DictTypeAssembler.buildDictDataVO(list));
        return ResultUtil.success(page);
    }

    @PostMapping("/dictType")
    @ApiOperation(value = "字典类型新增")
    public Result add(@RequestBody @Validated DictTypeDTO dictTypeDTO) {
        int registerResult = dictTypeService.add(dictTypeDTO);
        if (registerResult == 0) {
            return ResultUtil.success("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.fail("账号已存在");
        }
        return ResultUtil.fail("注册失败");
    }


    @GetMapping("/dictType/{id}")
    @ApiOperation(value = "字典类型详情查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id",value = "字典类型主键",dataType = "String",paramType = "path",required = true)}
    )
    public Result<DictTypeVO> getById(@PathVariable @NotBlank String id) {
        Optional<DictType> dictType = dictTypeService.getById(id);
        if (dictType.isPresent()) {
            return ResultUtil.success(DictTypeAssembler.buildDictDataVO(dictType.get()));
        }

        return ResultUtil.fail("字典类型未找到");
    }

}
