package com.muyuan.product.domains.service.cache;

import com.muyuan.common.core.bean.SelectTree;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.product.domains.dto.BrandDTO;
import com.muyuan.product.domains.repo.BrandRepo;
import com.muyuan.product.domains.service.BrandService;
import com.muyuan.product.domains.service.impl.BrandServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌Service业务层处理
 *
 * @author 2456910384
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Service
@Slf4j
public class BrandServiceCache extends BrandServiceImpl implements BrandService {

    private static String BRAND_KEY_PREFIX = "BRAND_OPTIONS:";

    private RedisCacheService redisCacheService;

    public BrandServiceCache(BrandRepo brandRepo, RedisCacheService redisCacheService) {
        super(brandRepo);
        this.redisCacheService = redisCacheService;
    }

    @Override
    public List<SelectTree> options(BrandDTO brandDTO) {
        String key = BRAND_KEY_PREFIX + brandDTO.getCategoryCode();
        return redisCacheService.getAndUpdateList(key,
                () -> super.options(brandDTO),
                SelectTree.class);
    }

    @Override
    public void linkCategory(BrandDTO brandDTO) {
        super.linkCategory(brandDTO);
        for (Long categoryCode : brandDTO.getCategoryCodes()) {
            redisCacheService.del(BRAND_KEY_PREFIX + categoryCode);
        }
    }
}
