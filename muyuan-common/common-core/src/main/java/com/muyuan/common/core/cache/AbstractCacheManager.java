package com.muyuan.common.core.cache;

import java.util.function.BiConsumer;
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
    public <T> T getAndUpdateCache(String keyPrefix, String key, Function<String,T> getCache, BiConsumer<String,T> setCache, Supplier<T> supplier, long expireTime, long nullExpire) {
        String newKey = keyPrefix + key;

        T result;
        boolean hasKey = hasKey(newKey);
        if (hasKey) {
            result = getCache.apply(newKey);
        } else {
            result = supplier.get();
            // 当result 为null时 同样设置 防止null值直接查询到数据库
            setCache.accept(newKey, result);

            // 第一次设置空值过期时间 以免常驻内存
            if (!hasKey && null == result && NOT_EXPIRE != expireTime) {
                expire(newKey, nullExpire);
            } else if (NOT_EXPIRE != expireTime) {
                expire(newKey, expireTime);
            }

        }


        return result;
    }
}
