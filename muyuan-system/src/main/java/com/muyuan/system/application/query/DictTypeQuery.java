package com.muyuan.system.application.query;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.application.vo.DictTypeVO;
import com.muyuan.system.interfaces.dto.DictTypeDTO;

public interface DictTypeQuery {

    /**
     * 通过DataType 查询字典数据
     * @param dictTypeDTO
     * @return
     */
    Page list(DictTypeDTO dictTypeDTO);
}
