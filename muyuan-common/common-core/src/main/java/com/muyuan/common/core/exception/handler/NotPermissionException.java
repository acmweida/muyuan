package com.muyuan.common.core.exception.handler;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;

public class NotPermissionException extends MuyuanException {

    public NotPermissionException() {
        this(ResponseCode.AUTH_FAIL.getCode(), ResponseCode.AUTH_FAIL.getMsg());
    }

    public NotPermissionException(int code, String message) {
        super(code, message);
    }
}
