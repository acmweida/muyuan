package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.application.service.DictDataService;
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

    private DictDataService dictDataService;

    @Override
    public Result<List<DictDataVO>> list(DictDataDTO dictDataDTO) {

        Page page  = dictDataService.list(dictDataDTO);
        List<DictData> list  = page.getRows();

        page.setRows(DictDataAssembler.buildDictDataVO(list));

        return ResultUtil.success(page);
    }

    @Override
    public Result<List<DictDataVO>> get(String dictType) {
        List<DictDataVO> res = new ArrayList<>();

        if (StringUtils.isBlank(dictType)) {
            return ResultUtil.success(res);
        }

        List<DictData> dictDatas = dictDataService.getByDataType(dictType);

        return ResultUtil.success(DictDataAssembler.buildDictDataVO(dictDatas));
    }

    @Override
    public Result add(DictDataDTO dictDataDTO) {
        Integer res = dictDataService.add(dictDataDTO);
        if (res == 0) {
            return ResultUtil.success();
        } else if (res == 1) {
            return ResultUtil.fail("已存在相同字典数据");
        }
        return ResultUtil.fail();
    }

    @Override
    @RequirePermissions(value = "system:dict:remove")
    public Result delete(String... ids) {
        if (dictDataService.deleteById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
