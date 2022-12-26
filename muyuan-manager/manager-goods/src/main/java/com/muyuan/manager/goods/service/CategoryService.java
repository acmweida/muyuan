package com.muyuan.manager.goods.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.goods.api.dto.CategoryDTO;
import com.muyuan.goods.api.dto.CategoryRequest;
import com.muyuan.manager.goods.dto.CategoryQueryParams;

import java.util.Optional;

/**
 * @ClassName GoodsCategoryDomainService 接口
 * Description 产品分类域服务
 * @Author 2456910384
 * @Date 2022/6/9 15:41
 * @Version 1.0
 */
public interface CategoryService {

    /**
     * 分类列表查询
     *
     * @param params
     * @return
     */
    Page<CategoryDTO> list(CategoryQueryParams params);


    /**
     * 新增分类
     *
     * @param request
     */
    Result add(CategoryRequest request);

    /**
     * 更新分类
     *
     * @param request
     */
    Result update(CategoryRequest request);


    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    Optional<CategoryDTO> get(Long id);

    /**
     * 获取详情
     *
     * @param code
     * @return
     */
    Optional<CategoryDTO> detail(Long code);

    /**
     * 删除ID
     *
     * @param ids
     */
    Result delete(Long ids);

}