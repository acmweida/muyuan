package com.muyuan.common.core.cache;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName CacheManager 接口
 * Description 缓存接口
 * @Author 2456910384
 * @Date 2022/2/14 16:54
 * @Version 1.0
 */
public interface CacheManager {

    long NOT_EXPIRE = -1;

    /**
     * 默认ExpireTime
     */
    long DEFAULT_EXPIRE_TIME = 5 * 60;

    /**
     * 获取缓存
     * @param keyPrefix
     * @param key
     * @return
     */
    Object get(String keyPrefix, String key);

    /**
     * 获取缓存并更新数据
     * @param keyPrefix
     * @param key
     * @param supplier
     * @return
     */
    Object get(String keyPrefix, String key, Supplier<String> supplier);

    /**
     * 获取缓存并更新数据
     * @param keyPrefix
     * @param key
     * @param supplier
     * @return
     */
    Object get(String keyPrefix, String key, Supplier<String> supplier,long expireTime);

    /**
     * 获取缓存并更新数据
     * @param keyPrefix
     * @param key
     * @param supplier
     * @return
     */
    Object get(String keyPrefix, String key, Supplier<String> supplier,long expireTime,long nullExpire);

    /**
     * 获取缓存
     * @param keyPrefix
     * @param key
     * @return
     */
    Set sGet(String keyPrefix, String key);

    /**
     * 获取缓存并更新数据
     * @param keyPrefix
     * @param key
     * @param supplier
     * @return
     */
    Set sGet(String keyPrefix, String key, Supplier<Set> supplier);

    /**
     * 获取缓存并更新数据
     * @param keyPrefix
     * @param key
     * @param supplier
     * @return
     */
    Set sGet(String keyPrefix, String key, Supplier<Set> supplier,long expireTime);

    /**
     * 获取缓存并更新数据
     * @param keyPrefix
     * @param key
     * @param supplier
     * @return
     */
    Set sGet(String keyPrefix, String key, Supplier<Set> supplier,long expireTime,long nullExpire);

    /**
     * 获取并更新缓存
     * @param keyPrefix
     * @param key
     * @param getCache
     * @param supplier
     * @param expireTime
     * @param nullExpire
     * @return
     */
    <T> T getAndUpdateCache(String keyPrefix, String key, Function<String,T> getCache, BiConsumer<String,T> setCache, Supplier<T> supplier, long expireTime , long nullExpire);

    /**
     * 判断key存在
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     * 设置过期时间
     * @param key
     * @param time
     * @return
     */
    boolean expire(String key, long time);
}
