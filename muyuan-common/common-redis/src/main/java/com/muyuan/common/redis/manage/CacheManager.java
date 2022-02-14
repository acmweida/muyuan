package com.muyuan.common.redis.manage;

import java.util.function.Supplier;

/**
 * @ClassName CacheManager 接口
 * Description 缓存接口
 * @Author 2456910384
 * @Date 2022/2/14 16:54
 * @Version 1.0
 */
public interface CacheManager {

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
    Object get(String keyPrefix, String key, Supplier supplier);
}
