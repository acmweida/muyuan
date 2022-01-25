package com.muyuan.common.domains.service.impl;

import com.muyuan.common.constant.RedisConst;
import com.muyuan.common.domains.manager.TokenManager;
import com.muyuan.common.domains.service.TokenService;
import com.muyuan.common.domains.vo.TokenVO;
import com.muyuan.common.enums.TokenStatus;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisTokenService
 * Description Redis TokenService实现
 * @Author 2456910384
 * @Date 2021/10/14 11:11
 * @Version 1.0
 */
public class RedisTokenService implements TokenService {

    private RedisTemplate redisTemplate;

    private static final int KEY_INDEX = 1 << 10;

    private TokenManager manager;

    public RedisTokenService(RedisTemplate redisTemplate,TokenManager manager) {
        this.redisTemplate = redisTemplate;
        this.manager = manager;
    }

    @Override
    public Optional<TokenVO> createToken() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String token = manager.createToken();
        valueOperations.set(RedisConst.TOKEN_KEY_PREFIX+token,RedisConst.SHORT_FALSE_VALUE,30, TimeUnit.MINUTES);
        DateTime now = DateTime.now();
        now.plusMinutes(30);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setToken(token);
        tokenVO.setExpireTime(now.toDate());
        return Optional.of(tokenVO);
    }

    @Override
    public Optional<TokenStatus> verify(String token) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(RedisConst.TOKEN_KEY_PREFIX + token);
        if ( null != o) {
            redisTemplate.delete(RedisConst.TOKEN_KEY_PREFIX+token);
            return Optional.of(TokenStatus.OK);
        }
        return Optional.of(TokenStatus.INVALID);
    }
}
