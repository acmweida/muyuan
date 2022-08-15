package com.muyuan.manager.system.domains.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.system.domains.model.DictType;
import com.muyuan.manager.system.domains.dto.DictTypeDTO;

import java.util.Date;

public class DictTypeFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static DictType newInstance(DictTypeDTO dictTypeDTO)  {
        DictType dictType = dictTypeDTO.convert();
        dictType.setCreateTime(new Date());
        dictType.setCreateBy(SecurityUtils.getUserId());
        return dictType;
    }
}
