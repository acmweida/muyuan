package com.muyuan.common.core.validator;

import com.muyuan.common.core.exception.ArgumentException;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

/**
 * @ClassName VailldUtil
 * Description 验证工具
 * @Author 2456910384
 * @Date 2022/12/1 10:19
 * @Version 1.0
 */
@Slf4j
public class ValidatorHolder {

   private  static final Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    private static Validator get() {
        return validator;
    }


    public static <T> T  validate(T bean, Class<?>... group) {
        Set<ConstraintViolation<T>> constraintViolations = ValidatorHolder.get().validate(bean, group);
        if (!constraintViolations.isEmpty()) {
            log.error("ValidatorHolder argument valid error : {}",constraintViolations);
            throw new ArgumentException(constraintViolations.iterator().next().getMessage());
        }
        return bean;
    }

}
