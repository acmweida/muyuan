package com.muyuan.common.core.cache;

import com.muyuan.common.core.util.FunctionUtil;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName CacheManager 接口
 * Description 缓存接口
 * @Author 2456910384
 * @Date 2022/2/14 16:54
 * @Version 1.0
 */
public interface CacheService {

    long NOT_EXPIRE = -1;

    /**
     * 默认ExpireTime
     */
    long DEFAULT_EXPIRE_TIME = 5 * 60;

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    Object get(String key);

    default Object getAndUpdate(String key, Function<String, Object> getCache, Supplier<Object> supplier, Consumer<Object> setCache) {
        return  FunctionUtil.getIfNullThenRebuild(
                () -> getCache.apply(key),
                supplier,
                setCache
        ).get();
    }

    default Object getAndUpdate(String key, Function<String, Object> getCache, Supplier<Object> supplier, BiConsumer<String,Object> setCache) {
        return  getAndUpdate(
                key,
                getCache,
                supplier,
                (value) -> {
                    setCache.accept(key,value);
                }
        );
    }

    default Object getAndUpdate(String key, Function<String, Object> getCache,Supplier<Object> supplier, BiConsumer<String,Object> setCache, long expireTime) {
        return getAndUpdate(key,getCache, supplier,setCache, expireTime, DEFAULT_EXPIRE_TIME);
    }

    default Object getAndUpdate(String key, Function<String, Object> getCache, Supplier<Object> supplier, BiConsumer<String,Object> setCache, long expireTime, long nullExpire) {
        return getAndUpdate(key, getCache, supplier,
                setCache.andThen(
                        (k,v) ->  {
                            if (v == null) {
                                expire(k,nullExpire);
                            } else {
                                expire(k,expireTime);
                            }
                        }
                ));
    }


    /**
     * 判断key存在
     *
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @return
     */
    boolean expire(String key, long time);
}
