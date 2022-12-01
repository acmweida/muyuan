package com.muyuan.common.core.validator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @ClassName VailldUtil
 * Description 验证工具
 * @Author 2456910384
 * @Date 2022/12/1 10:19
 * @Version 1.0
 */
public class ValidatorHolder {

   private  static final Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public static Validator get() {
        return validator;
    }

}
