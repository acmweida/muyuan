package com.muyuan.auth.base;

import com.muyuan.common.core.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthTokenAspect
 * Description 自定义返回内容
 * @Author 2456910384
 * @Date 2022/1/27 11:51
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class AuthTokenAspect {

    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public Object handleAccessTokenReturn(ProceedingJoinPoint pjp) throws Throwable {

        Object proceed = pjp.proceed();
        if (proceed != null) {
            ResponseEntity responseEntity = (ResponseEntity) proceed;
            if (responseEntity.getBody() instanceof OAuth2AccessToken) {
                return new ResponseEntity(ResultUtil.render(responseEntity.getBody()), HttpStatus.OK);
            }
            return proceed;
        }
        else {
            return proceed;
        }
    }

}
