package com.muyuan.common.mybatis.id;

import java.lang.annotation.*;

/**
 * @ClassName Id
 * Description 主键
 * @Author 2456910384
 * @Date 2022/6/8 9:46
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {

    String fieldName() default "id";

    boolean useGeneratedKeys() default false;

}
