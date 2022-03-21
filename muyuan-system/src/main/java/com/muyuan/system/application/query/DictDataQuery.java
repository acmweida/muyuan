package com.muyuan.system.application.query;

import com.muyuan.system.domain.model.DictData;

import java.util.List;

public interface DictDataQuery {

    /**
     * 通过DataType 查询字典数据
     * @param DataType
     * @return
     */
    List<DictData> getByDataType(String DataType);
}
