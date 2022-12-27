package com.muyuan.goods.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.goods.api.dto.AttributeDTO;
import com.muyuan.goods.api.dto.AttributeQueryRequest;
import com.muyuan.goods.api.dto.AttributeRequest;

/**
 * 类目属性Service接口
 *
 * @author ${author}
 * @date 2022-12-26T17:20:39.753+08:00
 */
public interface AttributeInterface {

    /**
      * 查询类目属性列表
      * @param request
      * @return
      */
    Result<Page<AttributeDTO>> list(AttributeQueryRequest request);

    /**
     * 添加类目属性
     * @param request
     * @return
     */
    Result add(AttributeRequest request);

    /**
     * 查询类目属性
     * @param id
     * @return
     */
    Result<AttributeDTO> getAttribute(Long id);

    /**
     * 更新类目属性
     * @param request
     * @return
     */
    Result updateAttribute(AttributeRequest request);

    /**
     *  删除类目属性
     * @param ids
     * @return
     */
    Result deleteAttribute(Long... ids);

}
