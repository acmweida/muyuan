package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.system.application.query.DictTypeQuery;
import com.muyuan.system.application.vo.DictTypeVO;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.interfaces.assembler.DictTypeAssembler;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import com.muyuan.system.interfaces.facade.controller.DictTypeController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DictTypeControllerImpl implements DictTypeController {

    private DictTypeQuery dictTypeQuery;

    @Override
    public Result<List<DictTypeVO>> list(DictTypeDTO dictTypeDTO) {
        List<DictType> list = dictTypeQuery.list(dictTypeDTO);

        return ResultUtil.success(DictTypeAssembler.buildDictDataVO(list));
    }
}
