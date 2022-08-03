package com.muyuan.product.domains.service.cache;

import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.product.domains.repo.BrandRepo;
import com.muyuan.product.domains.service.BrandService;
import com.muyuan.product.domains.service.impl.BrandServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 品牌Service业务层处理
 * 
 * @author 2456910384
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Service
@Slf4j
public class BrandServiceCache  extends BrandServiceImpl implements BrandService
{

    private RedisCacheService redisCacheService;

    public BrandServiceCache(BrandRepo brandRepo, RedisCacheService redisCacheService) {
        super(brandRepo);
        this.redisCacheService = redisCacheService;
    }
}
