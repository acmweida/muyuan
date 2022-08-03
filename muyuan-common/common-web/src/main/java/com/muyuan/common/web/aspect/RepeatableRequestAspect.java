package com.muyuan.common.web.aspect;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.redis.util.TokenUtil;
import com.muyuan.common.web.annotations.Repeatable;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
@AllArgsConstructor
public class RepeatableRequestAspect {


    @Around("@annotation(repeatable)")
    public Object repeatableAdvice(ProceedingJoinPoint pjp, Repeatable repeatable) throws Throwable {
        String token = (String) RequestContextHolder.getRequestAttributes().getAttribute(repeatable.varName(), RequestAttributes.SCOPE_REQUEST);
        if (ObjectUtils.isEmpty(token)) {
            return ResultUtil.fail(ResponseCode.TOKEN_NOT_FOUND_FAIL);
        }

        if (!TokenUtil.check(repeatable.businessType(),token)) {
            return ResultUtil.fail(ResponseCode.REPEATABLE_REQUEST_FAIL);
        }

         Object result = pjp.proceed();

         return result;

    }

}
