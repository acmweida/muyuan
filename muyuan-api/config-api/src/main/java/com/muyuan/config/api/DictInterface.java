package com.muyuan.config.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.config.api.dto.*;

import java.util.List;

/**
 * @ClassName DictTypeInterface 接口
 * Description 字典接口
 * @Author 2456910384
 * @Date 2022/10/14 9:52
 * @Version 1.0
 */
public interface DictInterface {

    Result<List<DictDataDTO>> getDictDataByType(DictQueryRequest request);

    Result<DictTypeDTO> getDictType(Long id);

    Result<DictDataDTO> getDictData(Long id);

    /**
     * 字典类型列表
     * @param request
     * @return
     */
    Result<Page<DictTypeDTO>> list(DictTypeQueryRequest request);

    /**
     * 字典数据列表
     * @param request
     * @return
     */
    Result<Page<DictDataDTO>> list(DictQueryRequest request);

    /**
     * 添加字典类型
     * @param request
     * @return
     */
    Result addDictType(DictTypeRequest request);

    /**
     * 添加字典数据
     * @param request
     * @return
     */
    Result addDictData(DictDataRequest request);

    /**
     * 更新字典数据
     * @param request
     * @return
     */
    Result updateDictData(DictDataRequest request);

    /**
     * 更新字典数据
     * @param request
     * @return
     */
    Result updateDictType(DictTypeRequest request);

    /**
     * 删除字典数据
     * @param ids
     * @return
     */
    Result deleteDictData(Long... ids);


    /**
     * 删除字典类型
     * @param ids
     * @return
     */
    Result deleteDictType(Long... ids);


}
