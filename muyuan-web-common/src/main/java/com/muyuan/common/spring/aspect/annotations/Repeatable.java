package com.muyuan.common.spring.aspect.annotations;

import com.muyuan.common.constant.CommonConst;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface Repeatable {
    String varName() default CommonConst.TOKEN;
}
