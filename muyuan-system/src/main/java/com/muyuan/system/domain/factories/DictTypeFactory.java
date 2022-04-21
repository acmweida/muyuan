package com.muyuan.system.domain.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.entity.DictTypeEntity;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import com.muyuan.system.domain.repo.DictTypeRepo;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class DictTypeFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static DictType newDictType(DictTypeDTO dictTypeDTO)  {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(dictTypeDTO,dictType);
        dictType.setCreateTime(new Date());
        dictType.setCreateBy(SecurityUtils.getUserId());
        return dictType;
    }
}
