package com.muyuan.common.core.validator.impl;

import com.muyuan.common.core.validator.annotions.NotNUllOnOther;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 *  依赖另一属性校验当前参数是否为空
 */
public class NotNullOnOtherValidator implements ConstraintValidator<NotNUllOnOther, Object> {

    private String field;

    private String other;

    private String[] otherValues;

    @Override
    public void initialize(NotNUllOnOther constraintAnnotation) {
        this.field = constraintAnnotation.value();
        this.other = constraintAnnotation.other();
        this.otherValues = constraintAnnotation.otherValues();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) {
            return false;
        }

        try {
            Object otherValue = getFieldValue(object, other);
            Object fieldValue = getFieldValue(object, field);
            for (String value : otherValues) {
                if (value.equals(otherValue) && ObjectUtils.isEmpty(fieldValue) ) {
                    return false;
                }
            }
            return true;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object getFieldValue(Object object, String fieldName) throws ReflectiveOperationException {
        if (object == null) {
            return null;
        }
        Class<?> aClass = object.getClass();
        Field field = aClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        Object fieldValue = field.get(object);
        return fieldValue;
    }
}
