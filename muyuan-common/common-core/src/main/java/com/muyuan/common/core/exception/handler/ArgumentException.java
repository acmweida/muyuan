package com.muyuan.common.core.exception.handler;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;

/**
 * @ClassName ArgumentException
 * Description 参数异常
 * @Author 2456910384
 * @Date 2022/5/18 13:49
 * @Version 1.0
 */
public class ArgumentException  extends MuyuanException {


    public ArgumentException(String message) {
        this(ResponseCode.ARGUMENT_ERROR.getCode(),message);
    }


    public ArgumentException(int code, String message) {
        super(code, message);
    }
}
