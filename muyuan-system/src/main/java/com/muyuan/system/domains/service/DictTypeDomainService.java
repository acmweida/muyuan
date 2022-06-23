package com.muyuan.system.domains.service;


import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domains.model.DictType;
import com.muyuan.system.domains.dto.DictTypeDTO;

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
    Page list(DictTypeDTO dictTypeDTO);

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
