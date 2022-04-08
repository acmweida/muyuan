package com.muyuan.system.application.service;


import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.interfaces.dto.DictTypeDTO;

import java.util.Optional;

/**
 * @ClassName DictTypeService
 * Description 字典类型
 * @Author 2456910384
 * @Date 2022/3/31 11:42
 * @Version 1.0
 */
public interface DictTypeService {

    /**
     * 通过DataType 查询字典数据
     * @param dictTypeDTO
     * @return
     */
    Page list(DictTypeDTO dictTypeDTO);

    /**
     * 新增
     * 0-注册成功 1-账户已存在
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
}
