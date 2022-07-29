package com.muyuan.product.domains.service.cache;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.repo.CategoryAttributeRepo;
import com.muyuan.product.domains.repo.GoodsCategoryRepo;
import com.muyuan.product.domains.service.CategoryAttributeService;
import com.muyuan.product.domains.service.GoodsCategoryService;
import com.muyuan.product.domains.service.impl.GoodsCategoryServiceImpl;
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
public class GoodsCategoryCache extends GoodsCategoryServiceImpl implements GoodsCategoryService {

    public GoodsCategoryCache(GoodsCategoryRepo goodsCategoryRepo, CategoryAttributeRepo categoryAttributeRepo, RedisCacheService redisCacheService) {
        super(goodsCategoryRepo, categoryAttributeRepo);
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
    public Optional<GoodsCategory> detail(GoodsCategory goodsCategory) {
        GoodsCategory category = redisCacheService.getAndUpdate(CATEGORY_KEY_PREFIX + goodsCategory.getCode(), () ->
                        goodsCategoryRepo.selectOne(GoodsCategory.builder()
                                .code(goodsCategory.getCode())
                                .build())
                , GoodsCategory.class);

        if (ObjectUtils.isEmpty(category)) {
            return Optional.empty();
        }

        List<CategoryAttribute> attributes = redisCacheService.getAndUpdateList(CategoryAttributeService.CATEGORY_ATTRIBUTE_KEY_PREFIX + goodsCategory.getCode(), () ->
                        categoryAttributeRepo.select(CategoryAttributeDTO.builder()
                                .categoryCode(goodsCategory.getCode())
                                .build())
                , CategoryAttribute.class);

        category.setAttributes(attributes);

        return Optional.of(category);
    }

    @Override
    public void delete(Long[] ids) {
        super.delete(ids);
        deleteCategoryCache();
    }

    @Override
    public void update(GoodsCategoryDTO goodsCategoryDTO) {
        super.update(goodsCategoryDTO);
        deleteCategoryCache(Long.valueOf(goodsCategoryDTO.getCode()));
    }
}
