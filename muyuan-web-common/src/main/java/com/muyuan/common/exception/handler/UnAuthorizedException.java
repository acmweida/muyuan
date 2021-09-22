package com.muyuan.common.exception.handler;

import com.muyuan.common.enums.ResponseCode;
import com.muyuan.common.exception.MuyuanException;
import com.muyuan.common.exception.MuyuanExceptionHandler;

public class UnAuthorizedException extends MuyuanException implements MuyuanExceptionHandler {

    public UnAuthorizedException() {
        this(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMsg());
    }

    public UnAuthorizedException(int code, String message) {
        super(code, message);
    }

}
