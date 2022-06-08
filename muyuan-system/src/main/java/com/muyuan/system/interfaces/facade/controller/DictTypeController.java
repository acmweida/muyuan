package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.service.DictTypeDomainService;
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

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"字典类型接口"})
@AllArgsConstructor
public class DictTypeController {

    private DictTypeDomainService dictTypeDomainService;

    @GetMapping("/dictType/list")
    @ApiOperation(value = "字典类型列表查询")
    public Result<List<DictTypeVO>> list(@ModelAttribute DictTypeDTO dictTypeDTO) {
        Page page = dictTypeDomainService.list(dictTypeDTO);
        List<DictType> list = page.getRows();

        page.setRows(DictTypeAssembler.buildDictDataVO(list));
        return ResultUtil.success(page);
    }

    @PostMapping("/dictType")
    @ApiOperation(value = "字典类型新增")
    public Result add(@RequestBody @Validated DictTypeDTO dictTypeDTO) {
        if (GlobalConst.NOT_UNIQUE.equals(dictTypeDomainService.checkUnique(new DictType(dictTypeDTO.getName(), dictTypeDTO.getType())))) {
            return ResultUtil.fail("账号已存在");
        }

        dictTypeDomainService.add(dictTypeDTO);
        return ResultUtil.success("注册成功");
    }


    @GetMapping("/dictType/{id}")
    @ApiOperation(value = "字典类型详情查询")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "字典类型主键", dataTypeClass = String.class, paramType = "path", required = true)}
    )
    public Result<DictTypeVO> getById(@PathVariable  String id) {
        if (StrUtil.isNumeric(id)) {
            Optional<DictType> dictType = dictTypeDomainService.getById(id);
            if (dictType.isPresent()) {
                return ResultUtil.success(DictTypeAssembler.buildDictDataVO(dictType.get()));
            }
        }
        return ResultUtil.fail("字典类型未找到");
    }

}
