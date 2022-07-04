package com.muyuan.product.domains.service.impl;

import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.service.BrandDomainService;
import com.muyuan.product.infrastructure.persistence.mapper.BrandMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌Service业务层处理
 * 
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Service
@AllArgsConstructor
@Slf4j
public class BrandDomainServiceImpl implements BrandDomainService
{
    private BrandMapper brandMapper;

    /**
     * 查询品牌
     * 
     * @param id 品牌主键
     * @return 品牌
     */
    @Override
    public Brand selectBrandById(Long id)
    {
        return brandMapper.selectBrandById(id);
    }

    /**
     * 查询品牌列表
     * 
     * @param brand 品牌
     * @return 品牌
     */
    @Override
    public List<Brand> selectBrandList(Brand brand)
    {
        return brandMapper.selectBrandList(brand);
    }

    /**
     * 新增品牌
     * 
     * @param brand 品牌
     * @return 结果
     */
    @Override
    public int insertBrand(Brand brand)
    {
        brand.setCreateTime(DateTime.now().toDate());
        return brandMapper.insertBrand(brand);
    }

    /**
     * 修改品牌
     * 
     * @param brand 品牌
     * @return 结果
     */
    @Override
    public int updateBrand(Brand brand)
    {
        brand.setUpdateTime(DateTime.now().toDate());
        return brandMapper.updateBrand(brand);
    }

    /**
     * 批量删除品牌
     * 
     * @param ids 需要删除的品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandByIds(Long[] ids)
    {
        return brandMapper.deleteBrandByIds(ids);
    }

    /**
     * 删除品牌信息
     * 
     * @param id 品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandById(Long id)
    {
        return brandMapper.deleteBrandById(id);
    }
}
