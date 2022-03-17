package com.muyuan.common.web.aspect;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.annotations.Repeatable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @ClassName RepeatableRequestAdvice
 * Description 接口幂等性保证
 * @Author 2456910384
 * @Date 2021/10/15 10:01
 * @Version 1.0
 */
@Aspect
@Component
public class RepeatableRequestAspect {

    @Autowired
    RedisTemplate redisTemplate;

    @Around("@annotation(repeatable)")
    public Object repeatableAdvice(ProceedingJoinPoint pjp, Repeatable repeatable) throws Throwable {
        Object token = RequestContextHolder.getRequestAttributes().getAttribute(repeatable.varName(), RequestAttributes.SCOPE_REQUEST);
        if (ObjectUtils.isEmpty(token)) {
            return ResultUtil.fail(ResponseCode.TOKEN_NOT_FOUND_FAIL);
        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String tokenKey = RedisConst.TOKEN_KEY_PREFIX+token;
        if (!redisTemplate.hasKey(tokenKey)) {
            return ResultUtil.fail(ResponseCode.TOKEN_INVALID_FAIL);
        }

        short v = (short) valueOperations.get(tokenKey);
        if (RedisConst.SHORT_TRUE_VALUE == v) {
            return ResultUtil.fail(ResponseCode.REPEATABLE_REQUEST_FAIL);
        }

        valueOperations.set(tokenKey,RedisConst.SHORT_TRUE_VALUE);

         Object result = pjp.proceed();

         return result;

    }

}
