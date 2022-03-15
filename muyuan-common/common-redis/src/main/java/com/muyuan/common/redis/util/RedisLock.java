package com.muyuan.common.redis.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Component
public class RedisLock {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public boolean tryLock(String key, long waitTime) {
        boolean lock = lock(key);
        if (lock) {
            return lock;
        }
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {

        }
        return lock(key);
    }

    public boolean lock(String key) {
        long currentTime = System.currentTimeMillis();
        if (redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(currentTime))) {
            return true;
        }

        String keyTime = redisTemplate.opsForValue().get(key);

        if (ObjectUtils.isEmpty(keyTime)) {
            if (redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(currentTime))) {
                return true;
            } else {
                return false;
            }
        }

        long keyCurrentTime = Long.parseLong(keyTime) + 3000;

        /**
         * 判断是否死锁
         */
        if (keyCurrentTime < currentTime) {
            redisTemplate.opsForValue().getOperations().delete(key);
            if (redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(currentTime))) {
                return true;
            }
        }
        return false;
    }

    public void unlock(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }


}
