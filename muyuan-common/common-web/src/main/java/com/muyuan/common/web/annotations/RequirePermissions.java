package com.muyuan.common.web.annotations;

import com.muyuan.common.core.constant.Logical;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequirePermissions {

    String[] value() default {};

    Logical logical() default Logical.AND;
}
