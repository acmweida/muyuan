package com.muyuan.common.mybatis.jdbc.crud;

public @interface ColumnExclude {

    String[] value() default {};
}
