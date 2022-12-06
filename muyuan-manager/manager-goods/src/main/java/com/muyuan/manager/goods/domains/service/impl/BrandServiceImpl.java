package com.muyuan.manager.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.SelectTree;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.manager.goods.domains.assembler.BrandAssembler;
import com.muyuan.manager.goods.domains.dto.BrandDTO;
import com.muyuan.manager.goods.domains.model.Brand;
import com.muyuan.manager.goods.domains.model.BrandCategory;
import com.muyuan.manager.goods.domains.repo.BrandRepo;
import com.muyuan.manager.goods.domains.service.BrandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 品牌Service业务层处理
 *
 * @author 2456910384
 * @date 2022-07-04T14:16:24.789+08:00
 */
@AllArgsConstructor
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    private static String BRAND_KEY_PREFIX = "BRAND_OPTIONS:";

    private RedisCacheService redisCacheService;

    private BrandRepo brandRepo;

    /**
     * 查询品牌
     *
     * @param id 品牌主键
     * @return 品牌
     */
    @Override
    public Optional<Brand> get(Long id) {
        return Optional.ofNullable(brandRepo.selectOne(
                Brand.builder()
                        .id(id)
                        .build()
        ));
    }

    /**
     * 查询品牌列表
     *
     * @param brandDTO 品牌
     * @return 品牌
     */
    @Override
    public Page<Brand> page(BrandDTO brandDTO) {
        Page page = Page.builder().pageNum(brandDTO.getPageNum())
                .pageSize(brandDTO.getPageSize()).build();
        page.setRows(brandRepo.select(brandDTO, page));
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
    public void add(BrandDTO brandDTO) {
        Brand brand = brandDTO.convert();
        brand.init();
        brand.save(brandRepo);
    }

    /**
     * 修改品牌
     *
     * @param brandDTO 品牌
     * @return 结果
     */
    @Override
    public void update(BrandDTO brandDTO) {
        Brand brand = brandDTO.convert();
        brand.update(brandRepo);
    }

    /**
     * 审核品牌
     *
     * @param brandDTO 品牌
     * @return 结果
     */
    @Override
    public void audit(BrandDTO brandDTO) {
        Brand brand = Brand.builder()
                .id(brandDTO.getId())
                .auditStatus(brandDTO.getAuditStatus())
                .build();
        brand.audit(brandRepo);
    }

    /**
     * 批量删除品牌
     *
     * @param ids 需要删除的品牌主键
     * @return 结果
     */
    @Override
    public void delete(Long... ids) {
        brandRepo.delete(ids);
    }

    @Override
    @Transactional
    public void linkCategory(BrandDTO brandDTO) {
        Brand brand = brandRepo.selectOne(Brand.builder()
                .id(brandDTO.getId())
                .build());
        if (ObjectUtils.isEmpty(brand)) {
            return;
        }

        Long[] categoryCodes = brandDTO.getCategoryCodes();
        if (ObjectUtils.isEmpty(categoryCodes)) {
            brandRepo.deleteLink(BrandCategory.builder()
                    .brandId(brand.getId())
                    .build());
        } else {
            List<BrandCategory> brandCategories = brandRepo.selectLinkCategoryCode(brand.getId());
            List<Long> codes = brandCategories.stream().map(BrandCategory::getCategoryCode).collect(Collectors.toList());
            List<Long> common = ListUtils.retainAll(Arrays.asList(categoryCodes), codes);

            // 删除
            List<BrandCategory> temp = new ArrayList<>();
            for (Long code : codes) {
                if (!common.contains(code)) {
                    temp.add(BrandCategory.builder()
                            .brandId(brand.getId())
                            .categoryCode(code)
                            .build());
                }
            }
            brandRepo.deleteLink(temp.toArray(new BrandCategory[0]));
            // 新增
            temp.clear();
            for (Long code : categoryCodes) {
                if (!common.contains(code)) {
                    temp.add(BrandCategory.builder()
                            .brandId(brand.getId())
                            .categoryCode(code)
                            .build());
                }
            }

            brandRepo.insertLink(temp);

        }

        for (Long categoryCode : brandDTO.getCategoryCodes()) {
            redisCacheService.del(BRAND_KEY_PREFIX + categoryCode);
        }

    }

    @Override
    public List<Long> getBrandCategory(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return Collections.EMPTY_LIST;
        }
        return brandRepo.selectLinkCategoryCode(id).stream().map(BrandCategory::getCategoryCode).collect(Collectors.toList());
    }

    @Override
    public List<SelectTree> options(BrandDTO brandDTO) {

        String key = BRAND_KEY_PREFIX + brandDTO.getCategoryCode();
        return CacheServiceUtil.getAndUpdateList(redisCacheService,key,
                () -> {
                    List<Brand> brands = brandRepo.selectBy(brandDTO);
                    return BrandAssembler.buildSelect(brands);
                },
                SelectTree.class);
    }

}
