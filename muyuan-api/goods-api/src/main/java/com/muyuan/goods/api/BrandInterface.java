package com.muyuan.goods.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.goods.api.dto.BrandQueryRequest;

/**
 * @ClassName BrandInterface 接口
 * Description 品牌
 * @Author 2456910384
 * @Date 2022/12/7 15:50
 * @Version 1.0
 */
public interface BrandInterface {

    /**
     * 菜单列表查询
     * @param request
     * @return
     */
    Result<Page<BrandDTO>> list(BrandQueryRequest request);

    /**
     * 获取品牌详情
     * @param id
     * @return
     */
    Result<BrandDTO> get(Long id);
}
