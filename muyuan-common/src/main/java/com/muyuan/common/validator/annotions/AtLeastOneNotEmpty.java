package com.muyuan.common.validator.annotions;

import com.muyuan.common.validator.impl.AtLeastOneNotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = AtLeastOneNotEmptyValidator.class)
@Documented
public @interface AtLeastOneNotEmpty {

    String message() default "至少有一个属性不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fields();
}
