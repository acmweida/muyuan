package com.muyuan.common.repo.jdbc.multi;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;

/**
 * @ClassName DateSourcePointCut
 * Description DateSourcePointCut 数据源拦截
 * @Author 2456910384
 * @Date 2021/12/9 10:00
 * @Version 1.0
 */
public class DateSourcePointCut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return AnnotatedElementUtils.hasAnnotation(method,DataSource.class) ||
                AnnotatedElementUtils.hasAnnotation(aClass,DataSource.class);
    }
}
