package com.muyuan.goods.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.goods.domains.enums.BrandAuthStatus;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.repo.BrandRepo;
import com.muyuan.goods.face.dto.BrandQueryCommand;
import com.muyuan.goods.infrastructure.repo.converter.BrandConverter;
import com.muyuan.goods.infrastructure.repo.dataobject.BrandCategoryDO;
import com.muyuan.goods.infrastructure.repo.dataobject.BrandDO;
import com.muyuan.goods.infrastructure.repo.mapper.BrandCategoryMapper;
import com.muyuan.goods.infrastructure.repo.mapper.BrandMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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

    private BrandConverter converter;

    @Override
    public Brand select(Long id, BrandAuthStatus... authStatuses) {
        BrandDO brandDO = brandMapper.selectOne(new LambdaQueryWrapper<BrandDO>()
                .eq(BrandDO::getId, id)
                .in(BrandDO::getAuditStatus, Arrays.stream(authStatuses).map(BrandAuthStatus::getCode).toArray()));
        return converter.to(brandDO);
    }

    @Override
    public List<Brand> selectByCategoryCode(Long... categoryCodes) {
        return converter.to(brandMapper.selectByCategoryCode(categoryCodes));
    }

    @Override
    public Page<Brand> select(BrandQueryCommand command) {
        LambdaQueryWrapper<BrandDO> wrapper = new LambdaQueryWrapper<BrandDO>()
                .like(BrandDO::getName, command.getName())
                .eq(BrandDO::getStatus, command.getStatus())
                .eq(BrandDO::getAuditStatus, command.getAuditStatus());

        Page<Brand> page = Page.<Brand>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<BrandDO> list = command.enablePage() ?
                brandMapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                brandMapper.selectList(wrapper);

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public Brand select(Brand.Identify identify) {
        BrandDO brandDO = brandMapper.selectOne(new LambdaQueryWrapper<BrandDO>().select(BrandDO::getId)
                .eq(BrandDO::getId, identify.getId())
                .eq(BrandDO::getName, identify.getName()));

        return converter.to(brandDO);
    }

    @Override
    @IdGenerator
    public boolean add(Brand permission) {
        BrandDO to = converter.to(permission);
        int count = brandMapper.insert(to);
        permission.setId(to.getId());
        return count > 0;
    }

    @Override
    public boolean update(Brand brand) {
        return brandMapper.updateById(converter.to(brand)) > 0;
    }

    @Override
    public List<Brand> deleteBy(Long... ids) {
        LambdaQueryWrapper<BrandDO> wrapper = new LambdaQueryWrapper<BrandDO>()
                .in(BrandDO::getId, ids);
        List<BrandDO> permissions = brandMapper.selectList(wrapper);

        brandMapper.delete(wrapper);

        return converter.to(permissions);
    }

    @Override
    public void deleteRef(List<Long> brandIds) {
        if (ObjectUtils.isEmpty(brandIds) || brandIds.size() == 0) {
            return;
        }
        brandCategoryMapper.delete(new LambdaQueryWrapper<BrandCategoryDO>()
                .in(BrandCategoryDO::getBrandId, brandIds));
    }


    @Override
    public void deleteRef(Long brandId, Long... categoryCodes) {
        if (ObjectUtils.isEmpty(brandId) || ObjectUtils.isEmpty(categoryCodes)) {
            return;
        }
        brandCategoryMapper.delete(new LambdaQueryWrapper<BrandCategoryDO>()
                .eq(BrandCategoryDO::getBrandId, brandId)
                .in(BrandCategoryDO::getCategoryCode, categoryCodes));
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
