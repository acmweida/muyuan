package com.muyuan.system.application.query;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.interfaces.dto.DictDataDTO;

import java.util.List;

public interface DictDataQuery {

    /**
     * 查询字典数据
     * @param dictDataDTO
     * @return
     */
   Page list(DictDataDTO dictDataDTO);

    /**
     * 通过DataType 查询字典数据
     * @param DataType
     * @return
     */
    List<DictData> getByDataType(String DataType);

}
