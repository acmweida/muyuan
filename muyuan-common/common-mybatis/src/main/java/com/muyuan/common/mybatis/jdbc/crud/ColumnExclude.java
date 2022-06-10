package com.muyuan.common.mybatis.jdbc.crud;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnExclude {

    String[] value() default {};
}
