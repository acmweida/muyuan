package com.muyuan.common.core.validator.impl;

import com.muyuan.common.core.validator.annotions.In;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 范围判断
 */
public class InValidator implements ConstraintValidator<In, Object> {

    private String[] value;

    @Override
    public void initialize(In constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) {
            return true;
        }

        for (String item : value) {
           if (item.equals(String.valueOf(object))) {
               return true;
           }
        }

        return false;
    }

}
