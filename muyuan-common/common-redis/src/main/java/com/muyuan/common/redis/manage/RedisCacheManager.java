package com.muyuan.common.redis.manage;

import com.muyuan.common.core.constant.GlobalConst;
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
        boolean hasKey = hasKey(newKey);
        if (hasKey) {
            result = get(newKey);
        } else {
           result = supplier.get();
        }
        // 当result 为null时 同样设置 防止null值直接查询到数据库
        set(newKey,result);

        // 第一次设置空值过期时间 以免常驻内存
        if ( !hasKey && null == result) {
            expire(newKey, 5 * 60);
        }

        return result;
    }
}
