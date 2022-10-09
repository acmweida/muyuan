package com.muyuan.common.core.util;

import com.muyuan.common.core.cache.CacheService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName CacheServiceUtil
 * Description 缓存工具
 * @Author 2456910384
 * @Date 2022/10/9 9:12
 * @Version 1.0
 */
public class CacheServiceUtil {

    private static Object getAndUpdate(String key, Function getCache, Supplier supplier, BiConsumer setCache) {
        return getAndUpdate(key, getCache, supplier, setCache, (k, v) -> {
        }, CacheService.DEFAULT_EXPIRE_TIME, CacheService.NOT_EXPIRE);
    }


    private static Object getAndUpdate(String key, Function getCache, Supplier supplier, BiConsumer setCache,
                                       BiConsumer expire) {
        return getAndUpdate(key, getCache, supplier, setCache, expire, CacheService.DEFAULT_EXPIRE_TIME, CacheService.NOT_EXPIRE);
    }

    private static Object getAndUpdate(String key, Function getCache, Supplier supplier, BiConsumer setCache,
                                       BiConsumer expire,
                                       long expireTime) {
        return getAndUpdate(key, getCache, supplier, setCache, expire, expireTime, CacheService.NOT_EXPIRE);
    }

    private static Object getAndUpdate(String key, Function getCache
            , Supplier supplier, BiConsumer setCache, BiConsumer expire, long expireTime, long nullExpire) {
        return FunctionUtil.getIfNullThenRebuild(
                () -> getCache.apply(key),
                supplier,
                (v) -> setCache.andThen(
                        (a,b) -> {
                            if (v == null && nullExpire > 0) {
                                expire.accept(key, nullExpire);
                            } else if (v == null && expireTime > 0) {
                                expire.accept(key, expireTime);
                            }
                        }
                ));
    }


    /* ================ String ================== */

    public static String getAndUpdate(CacheService cache, String key, Supplier<String> supplier) {
        return (String) getAndUpdate(key,
                (k) -> cache.get((String) k),
                () -> supplier.get(),
                (k, v) -> cache.set((String) k, (String) v)
        );
    }

    public static <T> T getAndUpdate(CacheService cache, String key, Supplier<Object> supplier, Class<T> type) {
        return (T) JSONUtil.parseObject(getAndUpdate(cache, key,
                () -> JSONUtil.toJsonString(supplier.get())
        ), type);
    }

    public static <T> List<T> getAndUpdateList(CacheService cache, String key, Supplier<Object> supplier, Class<T> type) {
        return (List<T>) JSONUtil.parseObjectList(getAndUpdate(cache, key,
                () -> JSONUtil.toJsonString(supplier.get())
        ), ArrayList.class, type);
    }
//
    // ============================Set(集合)=============================

    public static <T> Set<T> sGetAndUpdate(CacheService cache, String key, Supplier<Set<T>> supplier, Class<T> type) {
        return (Set<T>) getAndUpdate(key,
                (k) -> cache.sGet((String) k),
                supplier::get,
                (k, v) -> cache.sSet((String) k, (Set) v)
        );
    }
}
