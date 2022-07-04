package com.muyuan.product.domains.service;

import com.muyuan.product.domains.model.Brand;

import java.util.List;

/**
 * 品牌Service接口
 * 
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
public interface BrandDomainService
{
    /**
     * 查询品牌
     *
     * @param id 品牌主键
     * @return 品牌
     */
    Brand selectBrandById(Long id);

    /**
     * 查询品牌列表
     *
     * @param brand 品牌
     * @return 品牌集合
     */
    List<Brand> selectBrandList(Brand brand);

    /**
     * 新增品牌
     * 
     * @param brand 品牌
     * @return 结果
     */
    int insertBrand(Brand brand);

    /**
     * 修改品牌
     * 
     * @param brand 品牌
     * @return 结果
     */
    int updateBrand(Brand brand);

    /**
     * 批量删除品牌
     * 
     * @param ids 需要删除的品牌主键集合
     * @return 结果
     */
    int deleteBrandByIds(Long[] ids);

    /**
     * 删除品牌信息
     *
     * @param id 品牌主键
     * @return 结果
     */
    int deleteBrandById(Long id);
}
