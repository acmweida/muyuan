package com.muyuan.common.redis.manage;

import com.muyuan.common.redis.util.RedisUtils;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @ClassName RedisCacheManager
 * Description 缓存实现  Redis
 * @Author 2456910384
 * @Date 2022/2/14 17:05
 * @Version 1.0
 */
@Component
public class RedisCacheManager extends RedisUtils implements CacheManager{

    @Override
    public Object get(String keyPrefix, String key) {
        return get(keyPrefix+key);
    }

    @Override
    public Object get(String keyPrefix, String key, Supplier supplier) {
        String newKey = keyPrefix + key;

        Object result;
        if (hasKey(newKey)) {
            result = get(newKey);
        } else {
           result = supplier.get();
           set(newKey,result);
        }

        return result;
    }
}
