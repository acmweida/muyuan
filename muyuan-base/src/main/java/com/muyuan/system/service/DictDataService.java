package com.muyuan.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.config.api.dto.DictDataRequest;
import com.muyuan.system.dto.DictDataQueryParams;

import java.util.List;

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

}
