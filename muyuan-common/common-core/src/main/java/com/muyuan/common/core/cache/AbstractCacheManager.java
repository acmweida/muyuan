package com.muyuan.common.core.cache;

import com.muyuan.common.core.util.FunctionUtil;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName AbstractCacheManager
 * Description AbstractCacheManager
 * @Author 2456910384
 * @Date 2022/2/15 9:22
 * @Version 1.0
 */
public abstract class AbstractCacheManager implements CacheManager {

    @Override
    public Object getAndUpdate(String key, Function<String, Object> getCache, Supplier<Object> supplier, Consumer<Object> setCache) {
        return  FunctionUtil.getIfNullThenRebuild(
                () -> getCache.apply(key),
                supplier,
                setCache
        ).get();
    }

    @Override
    public Object getAndUpdate(String key, Function<String, Object> getCache, Supplier<Object> supplier, BiConsumer<String,Object> setCache) {
        return  getAndUpdate(
                key,
                getCache,
                supplier,
                (value) -> {
                    setCache.accept(key,value);
                }
        );
    }

    @Override
    public Object getAndUpdate(String key, Function<String, Object> getCache,Supplier<Object> supplier, BiConsumer<String,Object> setCache, long expireTime) {
        return getAndUpdate(key,getCache, supplier,setCache, expireTime, DEFAULT_EXPIRE_TIME);
    }

    @Override
    public Object getAndUpdate(String key, Function<String, Object> getCache, Supplier<Object> supplier, BiConsumer<String,Object> setCache, long expireTime, long nullExpire) {
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
}
