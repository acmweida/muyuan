package com.muyuan.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.system.dto.DictDataParams;
import com.muyuan.system.dto.DictDataQueryParams;

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

    Optional<DictDataDTO> getById(Long dictDataRequest);

    /**
     * 通过DataType 查询字典数据
     * @param dictType
     * @return
     */
    List<DictDataDTO> getByDataType(String dictType);


    /**
     * 字典数据添加
     * @param dictDataParams
     */
    Result add(DictDataParams dictDataParams);

    /**
     * 字典数据更新
     * @param dictDataParams
     * @return
     */
    Result update(DictDataParams dictDataParams);

    /**
     * 根据ID删除记录
     * @param ids
     * @return
     */
    Result deleteById(Long... ids);

}
