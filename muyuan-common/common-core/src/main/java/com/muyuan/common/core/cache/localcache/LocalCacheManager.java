package com.muyuan.common.core.cache.localcache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.muyuan.common.core.cache.CacheManager;
import com.muyuan.common.core.util.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @ClassName LocalCacheUtil
 * Description 本地缓存工具类
 * @Author 2456910384
 * @Date 2022/5/16 10:00
 * @Version 1.0
 */
public class LocalCacheManager extends ListCacheManager<Cache<String, Object>> implements CacheManager {


    private static final Cache<String, Object> normalCache = Caffeine.newBuilder().build();

    private static final List<Cache<String, Object>> expireCache = new ArrayList<>();

    private LocalCacheManager() {

    }

    private static LocalCacheManager instance = null;

    public static LocalCacheManager getInstance() {
        if (instance == null) {
            instance = new LocalCacheManager();
        }
        return instance;
    }

    @Override
    public boolean hasKey(String key) {
        return normalCache.asMap().containsKey(key);
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

    public Object getAndUpdate(String key, Supplier<Object> supplier) {
        return getAndUpdate(key,
                (k) -> get(k),
                () -> supplier.get(),
                (k, v) -> set(k, v)
        );
    }


    public Object getAndUpdate(String key, Supplier<Object> supplier, long expireTime) {
        return getAndUpdate(key,
                (k) -> get(k),
                () -> supplier.get(),
                (k, v) -> set(k, v, expireTime)
        );
    }


    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public void set(String key, Object value) {
        normalCache.put(key, value);
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

    public Object get(String key) {
        for (Cache cache : expireCache) {
            Object v = cache.getIfPresent(key);
            if (v != null) {
                return v;
            }
        }
        return normalCache.getIfPresent(key);
    }

    @Override
    protected Cache createExpireCache(long maxExpireTime) {
        return Caffeine.newBuilder().expireAfterWrite(maxExpireTime, TimeUnit.SECONDS).build();
    }
}
