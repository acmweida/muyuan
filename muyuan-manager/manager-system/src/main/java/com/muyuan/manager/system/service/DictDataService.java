package com.muyuan.manager.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.manager.system.domains.model.DictData;
import com.muyuan.manager.system.dto.DictDataQueryParams;
import com.muyuan.manager.system.dto.DictDataRequest;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DictDataService
 * Description 字典数据 Service
 * @Author 2456910384
 * @Date 2022/4/7 10:36
 * @Version 1.0
 */
public interface DictDataService {

    /**
     * 查询字典数据
     * @param params
     * @return
     */
    Page<DictDataDTO> page(DictDataQueryParams params);

    /**
     * 查询字典数据
     * @param dictDataRequest
     * @return
     */
    List<DictData> list(DictDataRequest dictDataRequest);

    Optional<DictDataDTO> getById(Long dictDataRequest);

    /**
     * 通过DataType 查询字典数据
     * @param dictType
     * @return
     */
    List<DictDataDTO> getByDataType(String dictType);


    /**
     * 字典数据添加
     * @param dictDataRequest
     */
    Result add(DictDataRequest dictDataRequest);

    /**
     * 字典数据更新
     * @param dictDataRequest
     * @return
     */
    void update(DictDataRequest dictDataRequest);

    /**
     * 根据ID删除记录
     * @param ids
     * @return
     */
    void deleteById(String... ids);

    /**
     * 检查唯一性
     *  类型 标签 值
     * @param dictData
     * @return
     */
    String checkUnique(DictData dictData);
}
