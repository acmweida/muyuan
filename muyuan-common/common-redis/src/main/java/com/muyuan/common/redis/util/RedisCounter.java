package com.muyuan.common.redis.util;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.global.AbstractCounter;
import com.muyuan.common.core.global.Counter;
import com.muyuan.common.redis.manage.RedisCacheService;

/**
 * @ClassName RedisCounter
 * Description Redis 实现
 * @Author 2456910384
 * @Date 2022/7/29 10:03
 * @Version 1.0
 */
public class RedisCounter extends AbstractCounter implements Counter {

    private static int DEFAULT_EXPIRE_TIME = 24 * 60 * 60 ;

    private int expireTime;

    private RedisCacheService redisCacheService;

    private RedisCounter(String name,int start,int step) {
        super(name,start,step);
    }

    public RedisCounter(String name, RedisCacheService redisCacheService) {
       this(name,redisCacheService,DEFAULT_START,DEFAULT_STEP,DEFAULT_EXPIRE_TIME);
    }

    public RedisCounter(String name, RedisCacheService redisCacheService, int start, int step,int expireTime) {
        this(name,start,step);
        this.expireTime = expireTime;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public int next() {
        String key = RedisConst.COUNT_KEY + name;
        return (int) redisCacheService.incr(key,step);
    }
}
