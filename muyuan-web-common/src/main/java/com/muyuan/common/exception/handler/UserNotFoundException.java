package com.muyuan.common.exception.handler;

import com.muyuan.common.enums.ResponseCode;
import com.muyuan.common.exception.MuyuanException;
import com.muyuan.common.exception.MuyuanExceptionHandler;

public class UserNotFoundException extends MuyuanException implements MuyuanExceptionHandler {

    public UserNotFoundException() {
        this(ResponseCode.USER_ONT_FOUND.getCode(), ResponseCode.USER_ONT_FOUND.getMsg());
    }

    public UserNotFoundException(int code, String message) {
        super(code, message);
    }
}
