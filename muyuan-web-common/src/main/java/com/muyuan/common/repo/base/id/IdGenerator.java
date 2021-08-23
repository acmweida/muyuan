package com.muyuan.common.repo.base.id;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdGenerator {

    String fieldName() default "id";

}
