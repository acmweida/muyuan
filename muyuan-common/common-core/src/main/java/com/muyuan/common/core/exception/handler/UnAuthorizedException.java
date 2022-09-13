package com.muyuan.common.core.exception.handler;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;

public class UnAuthorizedException extends MuyuanException {

    public UnAuthorizedException() {
        this(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMsg());
    }

    public UnAuthorizedException(int code, String message) {
        super(code, message);
    }

}
