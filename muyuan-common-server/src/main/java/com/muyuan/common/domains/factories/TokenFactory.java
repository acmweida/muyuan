package com.muyuan.common.domains.factories;

import com.muyuan.common.domains.manager.TokenManager;
import com.muyuan.common.domains.service.TokenService;
import com.muyuan.common.domains.service.impl.RedisTokenService;
import com.muyuan.common.spring.ApplicationContextHandler;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName TokenFactory
 * Description TODO
 * @Author 2456910384
 * @Date 2021/10/14 11:54
 * @Version 1.0
 */
public class TokenFactory {

    public static TokenService createRedisTokenService() {
        return new RedisTokenService(ApplicationContextHandler.getContext().getBean(RedisTemplate.class)
                ,ApplicationContextHandler.getContext().getBean(TokenManager.class));
    }
}
