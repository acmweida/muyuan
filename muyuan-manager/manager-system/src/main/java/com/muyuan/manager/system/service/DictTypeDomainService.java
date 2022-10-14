package com.muyuan.manager.system.service;


import com.muyuan.common.bean.Page;
import com.muyuan.manager.system.dto.DictTypeDTO;
import com.muyuan.manager.system.domains.model.DictType;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DictTypeService
 * Description 字典类型
 * @Author 2456910384
 * @Date 2022/3/31 11:42
 * @Version 1.0
 */
public interface DictTypeDomainService {

    /**
     * 通过DataType 查询字典数据
     * @param dictTypeDTO
     * @return
     */
    Page<DictType> list(DictTypeDTO dictTypeDTO);

    /**
     * 查询所有
     * @return
     */
    List<DictType> selectDictTypeAll();

    /**
     * 新增
     * @param dictTypeDTO
     * @return
     */
    int add(DictTypeDTO dictTypeDTO);


    /**
     * 字典详情查询
     * @param id
     * @return
     */
    Optional<DictType> getById(String id);

    /**
     * 检查唯一性
     * @param dictType
     * @return
     */
    String checkUnique(DictType dictType);
}
