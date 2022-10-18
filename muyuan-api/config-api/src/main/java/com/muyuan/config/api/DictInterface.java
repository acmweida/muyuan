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

    /**
     * 角色列表查询
     * @param request
     * @return
     */
    Result<Page<DictTypeDTO>> list(DictTypeQueryRequest request);


    Result addDictType(DictTypeRequest request);
}
