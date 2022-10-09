package com.muyuan.common.core.cache;

import java.util.Set;

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

    /* ===================== ==== String ==================*/
    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    boolean set(String key, String value, long time);


    /* ================== Set ================= */

    /**
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> Set<T> sGet(String key);

    /**
     *
     * @param key
     * @param values
     * @return
     */
    long sSet(String key,Set set);


}
