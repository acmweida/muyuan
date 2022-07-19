package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;
import com.muyuan.product.domains.model.BrandCategory;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.repo.CategoryAttributeRepo;
import com.muyuan.product.domains.repo.GoodsCategoryRepo;
import com.muyuan.product.domains.service.CategoryAttributeDomainService;
import com.muyuan.product.domains.service.GoodsCategoryDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName ProductCategoryDomainServiceImpl
 * Description 商品分类
 * @Author 2456910384
 * @Date 2022/6/10 8:57
 * @Version 1.0
 */
@Service
@Slf4j
@AllArgsConstructor
public class GoodsCategoryDomainServiceImpl implements GoodsCategoryDomainService {

    private GoodsCategoryRepo goodsCategoryRepo;

    private CategoryAttributeRepo categoryAttributeRepo;

    private RedisCacheService redisCacheService;

    @Override
    public List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO) {
        return goodsCategoryRepo.list(goodsCategoryDTO);
    }

    @Override
    @Transactional
    public void add(GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategory goodsCategory = goodsCategoryDTO.convert();
        goodsCategory.init();
        if (ObjectUtils.isNotEmpty(goodsCategory.getParentId())) {
            GoodsCategory parent = goodsCategoryRepo.selectOne(
                    GoodsCategory.builder().id(goodsCategoryDTO.getParentId()).build());
            if (ObjectUtils.isEmpty(parent)) {
                addRootCategory(goodsCategory);
            } else {
                addSubNodeCategory(goodsCategory, parent);
            }
        } else {
            addRootCategory(goodsCategory);
        }
    }

    private void addRootCategory(GoodsCategory goodsCategory) {
        int rootCount = goodsCategoryRepo.countAll(GoodsCategoryDTO.builder().level(1).build());
        goodsCategory.initRoot(rootCount);
        goodsCategory.save(goodsCategoryRepo);
        goodsCategory.setAncestors(String.valueOf(goodsCategory.getId()));
        goodsCategory.update(goodsCategoryRepo, "ancestors");
    }

    private void addSubNodeCategory(GoodsCategory goodsCategory, GoodsCategory parent) {
        goodsCategory.save(goodsCategoryRepo);
        // 查询兄弟节点数量 用于生成Code
        int count = goodsCategoryRepo.countAll(GoodsCategoryDTO.builder()
                .level(parent.getLevel() + 1)
                .parentId(parent.getId())
                .build());
        goodsCategory.linkParent(parent, count);
        goodsCategory.save(goodsCategoryRepo);
        parent.save(goodsCategoryRepo);
    }


    @Override
    public void update(GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategory goodsCategory = goodsCategoryDTO.convert();
        goodsCategory.save(goodsCategoryRepo);
        deleteCategoryCache(goodsCategory);
    }

    @Override
    public String checkUnique(GoodsCategory goodsCategory) {
        Long id = null == goodsCategory.getId() ? 0 : goodsCategory.getId();
        goodsCategory = goodsCategoryRepo.selectOne(
                GoodsCategory.builder().name(goodsCategory.getName())
                        .parentId(goodsCategory.getParentId()).build());
        if (null != goodsCategory && !id.equals(goodsCategory.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public Optional<GoodsCategory> get(GoodsCategory category) {
        GoodsCategory goodsCategory = goodsCategoryRepo.selectOne(category);
        return Optional.ofNullable(goodsCategory);
    }

    @Override
    public Optional<GoodsCategory> detail(GoodsCategory goodsCategory) {

        GoodsCategory category = redisCacheService.getAndUpdate(CATEGORY_KEY_PREFIX + goodsCategory.getCode(), () ->
                goodsCategoryRepo.selectOne(GoodsCategory.builder()
                        .code(goodsCategory.getCode())
                        .build())
        ,GoodsCategory.class);

        if (ObjectUtils.isEmpty(category)) {
            return Optional.empty();
        }

        List<CategoryAttribute> attributes = redisCacheService.getAndUpdateList(CategoryAttributeDomainService.CATEGORY_ATTRIBUTE_KEY_PREFIX + goodsCategory.getCode(), () ->
                        categoryAttributeRepo.select(CategoryAttributeDTO.builder()
                                .categoryCode(goodsCategory.getCode())
                                .build())
                , CategoryAttribute.class);

        category.setAttributes(attributes);

        return Optional.ofNullable(category);
    }

    @Override
    public void delete(Long[] ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return;
        }
        List<Long> toDelete = new ArrayList<>(Arrays.asList(ids));

        Long[] parentIds = ids;
        List<GoodsCategory> sub;

        List<GoodsCategory> list = goodsCategoryRepo.list(GoodsCategoryDTO.builder().ids(ids).build());
        List<Long> codes = list.stream().map(GoodsCategory::getCode).collect(Collectors.toList());
        do {
            sub = goodsCategoryRepo.list(GoodsCategoryDTO.builder().parentIds(parentIds).build());
            if (!sub.isEmpty()) {
                toDelete.addAll(sub.stream().map(GoodsCategory::getId).collect(Collectors.toList()));
                codes.addAll(sub.stream().map(GoodsCategory::getCode).collect(Collectors.toList()));
            }
        } while (!sub.isEmpty());

        List<BrandCategory> brandCategories = goodsCategoryRepo.selectBrand(codes.toArray(new Long[0]));
        if (ObjectUtils.isNotEmpty(brandCategories)) {
            throw new MuyuanException(ResponseCode.FAIL.getCode(), "有品牌关联当前分类");
        }

        goodsCategoryRepo.delete(toDelete.toArray(new Long[0]));

        redisCacheService.del(CATEGORY_KEY_PREFIX + RedisConst.ALL_PLACE_HOLDER);
    }


    private void deleteCategoryCache(GoodsCategory goodsCategory) {
        redisCacheService.del(CATEGORY_KEY_PREFIX + goodsCategory);
    }
}
