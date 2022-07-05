package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.BrandDTO;
import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.repo.BrandRepo;
import com.muyuan.product.domains.service.BrandDomainService;
import com.muyuan.product.infrastructure.persistence.mapper.BrandMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

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
    private BrandRepo brandRepo;

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
     * @param brandDTO 品牌
     * @return 品牌
     */
    @Override
    public Page<Brand> page(BrandDTO brandDTO)
    {
        Page page = Page.builder().pageNum(brandDTO.getPageNum())
                .pageSize(brandDTO.getPageSize()).build();
        page.setRows( brandRepo.select(brandDTO,page));
        return page;
    }


    @Override
    public String checkUnique(Brand brand) {
        Long id = null == brand.getId() ? 0 : brand.getId();
        brand = brandRepo.selectOne(brand);
        if (null != brand && !brand.getId().equals(id)) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    /**
     * 新增品牌
     * 
     * @param brandDTO 品牌
     * @return 结果
     */
    @Override
    public void add(BrandDTO brandDTO)
    {
        Brand brand = brandDTO.convert();
        brand.init();
        brand.save(brandRepo);
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
