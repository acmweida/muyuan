package com.muyuan.manager.goods.base.persistence;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.goods.base.persistence.mapper.BrandCategoryMapper;
import com.muyuan.manager.goods.base.persistence.mapper.BrandMapper;
import com.muyuan.manager.goods.dto.BrandParams;
import com.muyuan.manager.goods.dto.BrandQueryParams;
import com.muyuan.manager.goods.model.Brand;
import com.muyuan.manager.goods.model.BrandCategory;
import com.muyuan.manager.goods.repo.BrandRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 品牌对象 t_brand
 *
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Component
@AllArgsConstructor
public class BrandRepoImpl implements BrandRepo {

    private BrandMapper brandMapper;

    private BrandCategoryMapper brandCategoryMapper;

    @Override
    public List<Brand> select(BrandParams brandParams) {
        return select(brandParams, null);
    }

    @Override
    public List<Brand> select(BrandParams brandParams, Page page) {
        return brandMapper.selectList(new SqlBuilder(Brand.class)
                .like(NAME, brandParams.getName())
                .eq(STATUS, brandParams.getStatus())
                .eq(AUDIT_STATUS, brandParams.getAuditStatus())
                .eq(DEL,GlobalConst.FALSE)
                .page(page)
                .build());
    }

    @Override
    public Brand selectOne(Brand brand) {
        return brandMapper.selectOne(new SqlBuilder(Brand.class)
                .eq(ID, brand.getId())
                .eq(NAME, brand.getName())
                .build());
    }

    @Override
    public void insert(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateBy(brand, ID);
    }

    @Override
    public void update(Brand brand, String... column) {
        brandMapper.updateColumnBy(brand, column, ID);
    }

    @Override
    public void delete(Long... ids) {
        brandMapper.update(new SqlBuilder(Brand.class)
                .set(DEL, GlobalConst.TRUE)
                .in(ID, ids)
                .build());
    }

    @Override
    public List<BrandCategory> selectLinkCategoryCode(Long brandId) {
        return brandCategoryMapper.selectList(new SqlBuilder(BrandCategory.class)
                .eq(BRAND_ID, brandId)
                .build());
    }

    @Override
    @Transactional
    public void deleteLink(BrandCategory... brandCategorys) {
        for (BrandCategory brandCategory : brandCategorys) {
            brandCategoryMapper.deleteBy(new SqlBuilder(BrandCategory.class)
                    .eq(BRAND_ID, brandCategory.getBrandId())
                    .eq(CATEGORY_CODE,brandCategory.getCategoryCode())
                    .build());
        }
    }

    @Override
    public void insertLink(List<BrandCategory> brandCategories) {
        brandCategoryMapper.batchInsert(brandCategories);
    }

    @Override
    public List<Brand> selectBy(BrandQueryParams brandParams) {
        return brandMapper.selectBy(brandParams);
    }


}
