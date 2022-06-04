package com.muyuan.common.core.cache.localcache;

import com.muyuan.common.core.cache.AbstractCacheManager;
import com.muyuan.common.core.util.MathUtil;

import java.util.List;

/**
 * @ClassName ListCacheManager
 * Description 过期时间分开缓存操作
 * @Author 2456910384
 * @Date 2022/5/17 9:35
 * @Version 1.0
 */
public abstract class ListCacheManager<T> extends AbstractCacheManager {

    protected abstract T createExpireCache(long maxExpireTime);

    public int index(long time) {
        return MathUtil.bit(time);
    }

    public T buildExpireList(List<T> expireList, long expireTime) {
        int index = index(expireTime);
        if (expireList.size() >= index) {
            return expireList.get(index - 1);
        }

        int from = expireList.size();
        for (int i=from;i < index;i++) {
            expireList.add(createExpireCache(1L << i));
        }
        return expireList.get(index -1);
    }
}
