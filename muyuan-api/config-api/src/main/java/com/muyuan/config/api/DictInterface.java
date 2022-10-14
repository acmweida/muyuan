package com.muyuan.config.api;

import com.muyuan.common.bean.Result;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.config.api.dto.DictQueryRequest;

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
}
