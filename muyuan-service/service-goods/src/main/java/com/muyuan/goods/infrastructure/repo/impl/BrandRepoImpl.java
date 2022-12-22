package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.goods.domains.enums.BrandAuthStatus;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.repo.BrandRepo;
import com.muyuan.goods.face.dto.BrandQueryCommand;
import com.muyuan.goods.infrastructure.converter.BrandConverter;
import com.muyuan.goods.infrastructure.dataobject.BrandCategoryDO;
import com.muyuan.goods.infrastructure.dataobject.BrandDO;
import com.muyuan.goods.infrastructure.mapper.BrandCategoryMapper;
import com.muyuan.goods.infrastructure.mapper.BrandMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.muyuan.goods.infrastructure.mapper.BrandMapper.*;

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

    private BrandConverter converter;

    @Override
    public Brand select(Long id, BrandAuthStatus... authStatuses) {
        BrandDO brandDO = brandMapper.selectOne(new SqlBuilder(BrandDO.class)
                .eq(ID, id)
                .in(AUDIT_STATUS, Arrays.stream(authStatuses).map(BrandAuthStatus::getCode).toArray())
                .build());
        return converter.to(brandDO);
    }

    @Override
    public List<Brand> selectByCategoryCode(Long... categoryCodes) {
        return converter.to(brandMapper.selectByCategoryCode(categoryCodes));
    }

    @Override
    public Page<Brand> select(BrandQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(BrandDO.class)
                .like(NAME, command.getName())
                .eq(STATUS, command.getStatus())
                .eq(AUDIT_STATUS, command.getAuditStatus());

        Page<Brand> page = Page.<Brand>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<BrandDO> list = brandMapper.selectList(sqlBuilder.build());

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public Brand select(Brand.Identify identify) {
        BrandDO brandDO = brandMapper.selectOne(new SqlBuilder(BrandDO.class).select(ID)
                .eq(ID, identify.getId())
                .eq(NAME, identify.getName())
                .build());

        return converter.to(brandDO);
    }

    @Override
    public boolean add(Brand permission) {
        BrandDO to = converter.to(permission);
        Integer count = brandMapper.insert(to);
        permission.setId(to.getId());
        return count > 0;
    }

    @Override
    public boolean update(Brand brand) {
        return brandMapper.updateBy(converter.to(brand), ID) > 0;
    }

    @Override
    public List<Brand> deleteBy(Long... ids) {
        List<BrandDO> permissions = brandMapper.selectList(new SqlBuilder(BrandDO.class)
                .in(ID, ids)
                .build());

        brandMapper.deleteBy(new SqlBuilder().in(ID, ids).build());

        return converter.to(permissions);
    }

    @Override
    public void deleteRef(List<Long> brandIds) {
        if (ObjectUtils.isEmpty(brandIds) || brandIds.size() == 0) {
            return;
        }
        brandCategoryMapper.deleteBy(new SqlBuilder()
                .in(BrandCategoryMapper.BRAND_ID, brandIds)
                .build());
    }


    @Override
    public void deleteRef(Long brandId, Long... categoryCodes) {
        if (ObjectUtils.isEmpty(brandId) || ObjectUtils.isEmpty(categoryCodes)) {
            return;
        }
        brandCategoryMapper.deleteBy(new SqlBuilder()
                .eq(BrandCategoryMapper.BRAND_ID, brandId)
                .in(BrandCategoryMapper.CATEGORY_CODE, categoryCodes)
                .build());
    }

    @Override
    public void addRef(Long brandId, Long... categoryCodes) {
        if (categoryCodes.length == 0) {
            return;
        }
        List<BrandCategoryDO> brandCategoryDOS = new ArrayList<>();
        BrandCategoryDO.BrandCategoryDOBuilder builder = BrandCategoryDO.builder();
        for (Long categoryCode : categoryCodes) {
            brandCategoryDOS.add(builder.brandId(brandId)
                    .categoryCode(categoryCode)
                    .build());
        }
        brandCategoryMapper.batchInsert(brandCategoryDOS);
    }
}
