package com.muyuan.common.core.exception.handler;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;

public class ResourceNotFoundException extends MuyuanException {

    public ResourceNotFoundException() {
        this(ResponseCode.QUERY_NOT_EXIST.getCode(), ResponseCode.QUERY_NOT_EXIST.getMsg());
    }

    public ResourceNotFoundException(int code, String message) {
        super(code, message);
    }

    public ResourceNotFoundException(String message) {
        this(ResponseCode.QUERY_NOT_EXIST.getCode(), message);
    }
}
