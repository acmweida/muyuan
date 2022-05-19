package com.muyuan.system.domain.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.entity.DictTypeEntity;
import com.muyuan.system.interfaces.dto.DictTypeDTO;

import java.util.Date;

public class DictTypeFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static DictTypeEntity newInstance(DictTypeDTO dictTypeDTO)  {
        DictTypeEntity dictType = dictTypeDTO.convert();
        dictType.setCreateTime(new Date());
        dictType.setCreateBy(SecurityUtils.getUserId());
        return dictType;
    }
}
