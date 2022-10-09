package com.muyuan.common.core.cache.localcache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.muyuan.common.core.cache.CacheService;
import com.muyuan.common.core.util.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LocalCacheUtil
 * Description 本地缓存工具类
 * @Author 2456910384
 * @Date 2022/5/16 10:00
 * @Version 1.0
 */
public class LocalCacheService extends ListCacheService<Cache<String, Object>> implements CacheService {

    /**
     * 无过期时间
     */
    private static final Cache<String, Object> normalCache = Caffeine.newBuilder().build();

    /**
     * expireCache[index] 对应过期时间未 2 ^ (index -1) 到 2 ^ index 时间段内的缓存
     */
    private static final List<Cache<String, Object>> expireCache = new ArrayList<>();

    private LocalCacheService() {

    }

    private static LocalCacheService instance = null;

    public static LocalCacheService getInstance() {
        if (instance == null) {
            instance = new LocalCacheService();
        }
        return instance;
    }

    @Override
    public boolean hasKey(String key) {
        if (normalCache.asMap().containsKey(key)) {
            return true;
        }
        for (Cache<String, Object> cache : expireCache) {
            if (cache.asMap().containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean expire(String key, long time) {
        if (time <= 0) {
            Object v = get(key);
            if (v != null) {
                long expireTime = MathUtil.dtNextPow2(time);
                Cache<String, Object> cache = buildExpireList(expireCache, expireTime);
                cache.put(key, v);
            }
        }
        return false;
    }


    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, String value) {
        normalCache.put(key, value);
        return true;
    }

    @Override
    public boolean set(String key, String value, long time) {
        return false;
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public void set(String key, Object value, long expireTime) {
        Cache<String, Object> stringObjectCache = buildExpireList(expireCache, expireTime);
        stringObjectCache.put(key, value);
    }

    @Override
    public <T> Set<T> sGet(String key) {
        for (Cache cache : expireCache) {
            Object v = cache.getIfPresent(key);
            if (v != null) {
                return (Set<T>) v;
            }
        }
        return (Set<T>) normalCache.getIfPresent(key);
    }

    @Override
    public long sSet(String key, Set set) {
        normalCache.put(key, set);
        return set.size();
    }


    public String get(String key) {
        for (Cache cache : expireCache) {
            Object v = cache.getIfPresent(key);
            if (v != null) {
                return (String) v;
            }
        }
        return (String) normalCache.getIfPresent(key);
    }

    @Override
    protected Cache createExpireCache(long maxExpireTime) {
        return Caffeine.newBuilder().expireAfterWrite(maxExpireTime, TimeUnit.SECONDS).build();
    }
}
