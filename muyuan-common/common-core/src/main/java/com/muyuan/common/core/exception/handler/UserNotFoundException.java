package com.muyuan.common.core.exception.handler;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;

public class UserNotFoundException extends MuyuanException {

    public UserNotFoundException() {
        this(ResponseCode.USER_ONT_FOUND.getCode(), ResponseCode.USER_ONT_FOUND.getMsg());
    }

    public UserNotFoundException(int code, String message) {
        super(code, message);
    }
}
