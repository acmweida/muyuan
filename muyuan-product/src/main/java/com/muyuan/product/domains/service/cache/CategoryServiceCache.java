package com.muyuan.product.domains.service.cache;

import com.muyuan.common.core.bean.SelectTree;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.product.domains.dto.AttributeDTO;
import com.muyuan.product.domains.dto.CategoryDTO;
import com.muyuan.product.domains.model.Attribute;
import com.muyuan.product.domains.model.Category;
import com.muyuan.product.domains.repo.AttributeRepo;
import com.muyuan.product.domains.repo.CategoryRepo;
import com.muyuan.product.domains.service.AttributeService;
import com.muyuan.product.domains.service.CategoryService;
import com.muyuan.product.domains.service.impl.CategoryServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName GoodsCategoryCache
 * Description 商品分类缓存
 * @Author 2456910384
 * @Date 2022/7/29 16:56
 * @Version 1.0
 */
@Service
public class CategoryServiceCache extends CategoryServiceImpl implements CategoryService {

    private static String CATEGORY_KEY_PREFIX = "category:";

    public CategoryServiceCache(CategoryRepo categoryRepo, AttributeRepo attributeRepo, RedisCacheService redisCacheService) {
        super(categoryRepo, attributeRepo);
        this.redisCacheService = redisCacheService;
    }

    private RedisCacheService redisCacheService;

    private void deleteCategoryCache() {
        redisCacheService.del(CATEGORY_KEY_PREFIX + RedisConst.ALL_PLACE_HOLDER);
    }

    private void deleteCategoryCache(Long categoryCode) {
        redisCacheService.del(CATEGORY_KEY_PREFIX + categoryCode);
    }

    @Override
    public List<SelectTree> treeSelect(Long parentId, Integer level) {
        String key = CATEGORY_KEY_PREFIX + (parentId == null ? "" : parentId) + ":"
                + (level == null ? "" : level);
        return redisCacheService.getAndUpdateList(key,
                () -> super.treeSelect(parentId, level),
                SelectTree.class);
    }

    @Override
    public Optional<Category> detail(Category goodsCategory) {
        Category category = redisCacheService.getAndUpdate(CATEGORY_KEY_PREFIX + goodsCategory.getCode(), () ->
                        categoryRepo.selectOne(Category.builder()
                                .code(goodsCategory.getCode())
                                .build())
                , Category.class);

        if (ObjectUtils.isEmpty(category)) {
            return Optional.empty();
        }

        List<Attribute> attributes = redisCacheService.getAndUpdateList(AttributeService.CATEGORY_ATTRIBUTE_KEY_PREFIX + goodsCategory.getCode(), () ->
                        attributeRepo.select(AttributeDTO.builder()
                                .categoryCode(goodsCategory.getCode())
                                .build())
                , Attribute.class);

        category.setAttributes(attributes);

        return Optional.of(category);
    }

    @Override
    public void delete(Long[] ids) {
        super.delete(ids);
        deleteCategoryCache();
    }


    @Override
    public void update(CategoryDTO categoryDTO) {
        super.update(categoryDTO);
        deleteCategoryCache(Long.valueOf(categoryDTO.getCode()));
    }
}
