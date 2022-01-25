package com.muyuan.common.spring.aspect.annotations;

import com.muyuan.common.constant.CommonConst;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 幂等注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Repeatable {
    String varName() default CommonConst.TOKEN;
}
