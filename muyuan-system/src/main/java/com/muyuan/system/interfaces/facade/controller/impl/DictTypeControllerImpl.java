package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.application.service.DictTypeService;
import com.muyuan.system.application.vo.DictTypeVO;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.interfaces.assembler.DictTypeAssembler;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import com.muyuan.system.interfaces.facade.controller.DictTypeController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DictTypeControllerImpl implements DictTypeController {

    private DictTypeService dictTypeService;

    @Override
    public Result<List<DictTypeVO>> list(DictTypeDTO dictTypeDTO) {
        Page page  = dictTypeService.list(dictTypeDTO);
        List<DictType> list  = page.getRows();

        page.setRows(DictTypeAssembler.buildDictDataVO(list));
        return ResultUtil.success(page);
    }

    @Override
    public Result add(DictTypeDTO dictTypeDTO) {
        int registerResult = dictTypeService.add(dictTypeDTO);
        if (registerResult == 0) {
            return ResultUtil.success("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.fail("账号已存在");
        }
        return ResultUtil.fail("注册失败");
    }

    @Override
    public Result<DictTypeVO> getById(String id) {
        Optional<DictType> dictType = dictTypeService.getById(id);
        if (dictType.isPresent()) {
            return ResultUtil.success(DictTypeAssembler.buildDictDataVO(dictType.get()));
        }

        return ResultUtil.fail("字典类型未找到");
    }
}
