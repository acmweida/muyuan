package com.muyuan.common.core.exception.handler;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.core.exception.MuyuanExceptionHandler;

public class UnAuthorizedException extends MuyuanException implements MuyuanExceptionHandler {

    public UnAuthorizedException() {
        this(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMsg());
    }

    public UnAuthorizedException(int code, String message) {
        super(code, message);
    }

}
