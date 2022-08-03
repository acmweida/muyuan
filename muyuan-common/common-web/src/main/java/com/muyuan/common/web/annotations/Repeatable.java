package com.muyuan.common.web.annotations;



import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.BusinessType;

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
    String varName() default GlobalConst.TOKEN;

    BusinessType businessType() default BusinessType.DEFAULT;
}
