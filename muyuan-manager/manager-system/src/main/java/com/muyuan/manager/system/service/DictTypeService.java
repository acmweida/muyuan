package com.muyuan.manager.system.service;


import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.config.api.dto.DictTypeDTO;
import com.muyuan.manager.system.dto.DictTypeParams;
import com.muyuan.manager.system.dto.DictTypeQueryParams;

import java.util.List;
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
    Page<DictTypeDTO> list(DictTypeQueryParams dictTypeDTO);

    /**
     * 查询所有
     * @return
     */
    List<DictTypeDTO> selectDictTypeAll();

    /**
     * 新增
     * @param dictTypeParams
     * @return
     */
    Result add(DictTypeParams dictTypeParams);


    /**
     * 字典详情查询
     * @param id
     * @return
     */
    Optional<DictTypeDTO> getById(Long id);

    /**
     * 字典类型更新
     * @param dictTypeParams
     * @return
     */
    Result update(DictTypeParams dictTypeParams);


    /**
     * 根据ID删除记录
     * @param ids
     * @return
     */
    Result deleteById(Long... ids);

}
