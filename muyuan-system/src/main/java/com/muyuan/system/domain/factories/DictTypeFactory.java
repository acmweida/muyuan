package com.muyuan.system.domain.factories;

import com.muyuan.system.domain.entity.DictTypeEntity;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import com.muyuan.system.domain.repo.DictTypeRepo;
import org.springframework.beans.BeanUtils;

public class DictTypeFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static DictTypeEntity newDictTypeEntity(DictTypeDTO dictTypeDTO)  {
        DictTypeEntity dictTypeEntity = new DictTypeEntity();
        BeanUtils.copyProperties(dictTypeDTO,dictTypeEntity);
        return dictTypeEntity;
    }
}
