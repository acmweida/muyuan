package com.muyuan.product.infrastructure.persistence.mapper;

import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.infrastructure.config.mybatis.ProductBaseMapper;

import java.util.List;

/**
 * 品牌Mapper接口
 * 
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
public interface BrandMapper extends ProductBaseMapper<Brand> {

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
     * 删除品牌
     *
     * @param id 品牌主键
     * @return 结果
     */
    int deleteBrandById(Long id);

    /**
     * 批量删除品牌
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBrandByIds(Long[] ids);
}
