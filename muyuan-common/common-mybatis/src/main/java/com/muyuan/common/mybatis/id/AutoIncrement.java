package com.muyuan.common.mybatis.id;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoIncrement {

    boolean useGeneratedKeys() default true;
}
