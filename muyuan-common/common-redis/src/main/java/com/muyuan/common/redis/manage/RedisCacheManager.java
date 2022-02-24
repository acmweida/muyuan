package com.muyuan.common.redis.manage;

import com.muyuan.common.redis.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @ClassName RedisCacheManager
 * Description 缓存实现  Redis
 * @Author 2456910384
 * @Date 2022/2/14 17:05
 * @Version 1.0
 */
@Component
public class RedisCacheManager extends AbstractCacheManager implements CacheManager {

    @Autowired
    public RedisUtils redisUtils;

    @Override
    public Object get(String keyPrefix, String key) {
        return redisUtils.get(keyPrefix + key);
    }

    @Override
    public Object get(String keyPrefix, String key, Supplier<String> supplier) {
        return get(keyPrefix, key, supplier, NOT_EXPIRE);
    }

    @Override
    public Object get(String keyPrefix, String key, Supplier<String> supplier, long expireTime) {
        return get(keyPrefix, key, supplier, expireTime, DEFAULT_EXPIRE_TIME);
    }

    @Override
    public Object get(String keyPrefix, String key, Supplier<String> supplier, long expireTime, long nullExpire) {
        return getAndUpdateCache(keyPrefix, key, (k) -> (String) redisUtils.get(k), (k, v) -> redisUtils.set(k, v), supplier, expireTime, nullExpire);
    }

    @Override
    public Set<Object> sGet(String keyPrefix, String key) {
        return redisUtils.sGet(keyPrefix + key);
    }

    @Override
    public Set<String> sGet(String keyPrefix, String key, Supplier<Set> supplier) {
        return sGet(keyPrefix, key, supplier, NOT_EXPIRE);
    }

    @Override
    public Set<String> sGet(String keyPrefix, String key, Supplier<Set> supplier, long expireTime) {
        return sGet(keyPrefix, key, supplier, expireTime, DEFAULT_EXPIRE_TIME);
    }

    @Override
    public Set sGet(String keyPrefix, String key, Supplier<Set> supplier, long expireTime, long nullExpire) {
        return getAndUpdateCache(keyPrefix, key, (k) -> redisUtils.sGet(k), (k, v) -> redisUtils.sSet(k, v.toArray()), supplier, expireTime, nullExpire);
    }

    @Override
    public boolean hasKey(String key) {
        return redisUtils.hasKey(key);
    }

    @Override
    public boolean expire(String key, long time) {
        return redisUtils.expire(key, time);
    }
}
