package com.muyuan.common.redis.util;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.core.enums.BusinessType;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TokenUtil
 * Description TODO
 * @Author 2456910384
 * @Date 2022/8/3 15:43
 * @Version 1.0
 */
public class TokenUtil {

    private static int DEFAULT_EXPIRE_TIME = 24 * 60 * 60 ;

    private static RedisTemplate redisTemplate = ApplicationContextHandler.getContext().getBean("redisTemplate",RedisTemplate.class);

    /**
     * @param businessType 业务类型枚举
     * @return
     */
    public static String generate(BusinessType businessType) {
        String token = UUID.randomUUID().toString();
        String key = RedisConst.TOKEN_KEY_PREFIX + businessType.getValue() + ":" + token;
        redisTemplate.opsForValue().set(RedisConst.TOKEN_KEY_PREFIX + key, token,DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
        return token;
    }

    public static boolean check(BusinessType businessType,String token) {
        String key = RedisConst.TOKEN_KEY_PREFIX + businessType.getValue() + ":" + token;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}
