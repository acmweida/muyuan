package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.system.application.query.DictDataQuery;
import com.muyuan.system.application.vo.DictDataVO;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.interfaces.assembler.DictDataAssembler;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import com.muyuan.system.interfaces.facade.controller.DictDataController;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DictDataControllerImpl implements DictDataController {

    private DictDataQuery dictDataQuery;

    @Override
    public Result<List<DictDataVO>> get(String dictType) {
        List<DictDataVO> res = new ArrayList<>();

        if (StringUtils.isBlank(dictType)) {
            return ResultUtil.success(res);
        }

        List<DictData> dictDatas = dictDataQuery.getByDataType(dictType);

        return ResultUtil.success(DictDataAssembler.buildDictDataVO(dictDatas));
    }

    @Override
    public Result<List<DictDataVO>> add(DictDataDTO dictDataDTO) {
        return null;
    }
}
