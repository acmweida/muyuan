package com.muyuan.system.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.system.dto.converter.DictConverter;
import com.muyuan.system.dto.vo.DictDataVO;
import com.muyuan.system.service.DictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = {"字典数据接口"})
@AllArgsConstructor
public class DictDataController {

    private DictDataService dictDataService;

    private DictConverter converter;

    @GetMapping("/dictData/{dictType}")
    @ApiOperation(value = "字典类型数值查询")
    public Result<List<DictDataVO>> get(@PathVariable String dictType) {
        List<DictDataVO> res = new ArrayList<>();

        if (StringUtils.isBlank(dictType)) {
            return ResultUtil.success(res);
        }

        List<DictDataDTO> dictDatas = dictDataService.getByDataType(dictType);

        return ResultUtil.success(converter.toDictDataVO(dictDatas));
    }


}
