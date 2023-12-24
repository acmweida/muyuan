package com.muyuan.common.core.exception;

import com.muyuan.common.core.enums.ResponseCode;

import java.io.Serial;

/**
 * @ClassName ArgumentException
 * Description 参数异常
 * @Author 2456910384
 * @Date 2022/5/18 13:49
 * @Version 1.0
 */
public class ArgumentException  extends MuyuanException {


    @Serial
    private static final long serialVersionUID = 6546669146555765604L;

    public ArgumentException(String message) {
        this(ResponseCode.ARGUMENT_ERROR.getCode(),message);
    }


    public ArgumentException(int code, String message) {
        super(code, message);
    }
}
