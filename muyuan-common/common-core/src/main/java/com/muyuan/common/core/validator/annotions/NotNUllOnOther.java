package com.muyuan.common.core.validator.annotions;

import com.muyuan.common.core.validator.impl.NotNullOnOtherValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 一个参数非空取决于另一参数
 */
@Target( { TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NotNullOnOtherValidator.class)
@Documented
@NotNull
public @interface NotNUllOnOther {

    String value();

    String other();

    String[] otherValues() default {};

    String message() default "当另依赖属性存在时，此属性不能为空";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
