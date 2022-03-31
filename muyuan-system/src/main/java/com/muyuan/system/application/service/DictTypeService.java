package com.muyuan.system.application.service;


import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.interfaces.dto.DictTypeDTO;

import java.util.List;

/**
 * @ClassName DictTypeService
 * Description 字典类型
 * @Author 2456910384
 * @Date 2022/3/31 11:42
 * @Version 1.0
 */
public interface DictTypeService {

    List<DictType> list(DictTypeDTO dictTypeDTO);

    /**
     * 新增
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int add(DictTypeDTO dictTypeDTO);
}
