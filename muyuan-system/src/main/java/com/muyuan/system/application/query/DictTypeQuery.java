package com.muyuan.system.application.query;

import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.interfaces.dto.DictTypeDTO;

import java.util.List;

public interface DictTypeQuery {

    /**
     * 通过DataType 查询字典数据
     * @param dictTypeDTO
     * @return
     */
    List<DictType> list(DictTypeDTO dictTypeDTO);
}
