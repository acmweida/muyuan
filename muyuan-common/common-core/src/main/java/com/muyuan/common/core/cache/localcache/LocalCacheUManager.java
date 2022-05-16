package com.muyuan.common.core.cache.localcache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.muyuan.common.core.cache.AbstractCacheManager;
import com.muyuan.common.core.cache.CacheManager;

import java.util.Set;
import java.util.function.Supplier;

/**
 * @ClassName LocalCacheUtil
 * Description 本地缓存工具类
 * @Author 2456910384
 * @Date 2022/5/16 10:00
 * @Version 1.0
 */
public class LocalCacheUManager extends AbstractCacheManager implements CacheManager {


    private static final Cache<String, Object> normalCache = Caffeine.newBuilder().build();

    @Override
    public Object get(String keyPrefix, String key) {
        return get(keyPrefix + key);
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
        return getAndUpdateCache(keyPrefix, key, (k) -> (String) get(k), (k, v) -> set(k, v), supplier, expireTime, nullExpire);
    }

    @Override
    public Set<String> sGet(String keyPrefix, String key) {
        return sGet(keyPrefix + key);
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
        return getAndUpdateCache(keyPrefix, key, (k) -> sGet(k), (k, v) -> sSet(k, v.toArray()), supplier, expireTime, nullExpire);
    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

    @Override
    public boolean expire(String key, long time) {
        return false;
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        normalCache.put(key,value);
        return true;
    }

    public String get(String key) {
        return (String) normalCache.getIfPresent(key);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public void sSet(String key, Object... values) {
        normalCache.put(key,values);
    }

    public Set sGet(String key) {
        return (Set) normalCache.getIfPresent(key);
    }

}
