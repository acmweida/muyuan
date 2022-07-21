package com.muyuan.common.core.validator.impl;

import com.muyuan.common.core.validator.annotions.AtLeastOneNotEmpty;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * 至少许传一项校验
 */
public class AtLeastOneNotEmptyValidator implements ConstraintValidator<AtLeastOneNotEmpty, Object> {

    private String[] fieldNames;

    @Override
    public void initialize(AtLeastOneNotEmpty constraintAnnotation) {
        this.fieldNames = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) {
            return true;
        }
        try {
            for (String fieldName : fieldNames) {
                Object filedValue = null;
                filedValue = getFieldValue(object, fieldName);
                if (!ObjectUtils.isEmpty(filedValue)) {
                    return true;
                }
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return false;
        }
        return false;
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
