package com.muyuan.goods.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.goods.api.dto.CategoryDTO;
import com.muyuan.goods.api.dto.CategoryQueryRequest;
import com.muyuan.goods.api.dto.CategoryRequest;

import java.util.List;


/**
 * 商品分类Service接口
 *
 * @author ${author}
 * @date 2022-12-16T11:54:09.147+08:00
 */
public interface CategoryInterface {

    /**
      * 查询商品分类列表
      * @param request
      * @return
      */
    Result<Page<CategoryDTO>> list(CategoryQueryRequest request);

    /**
     * 根据品牌ID查询关联类目
     * @param brandIds
     * @return
     */
    Result<List<CategoryDTO>> listByBrandId(Long... brandIds);

    /**
     * 添加商品分类
     * @param request
     * @return
     */
    Result add(CategoryRequest request);

    /**
     * 查询商品分类
     * @param id
     * @return
     */
    Result<CategoryDTO> getCategory(Long id);

    /**
     * 更新商品分类
     * @param request
     * @return
     */
    Result updateCategory(CategoryRequest request);

    /**
     *  删除商品分类
     * @param ids
     * @return
     */
    Result deleteCategory(Long... ids);

}
