package com.muyuan.common.core.exception.handler;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;

/**
 * @ClassName FailException
 * Description 操作失败
 * @Author 2456910384
 * @Date 2022/6/2 14:31
 * @Version 1.0
 */
public class FailException extends MuyuanException {

    public FailException( String message) {
        super(ResponseCode.FAIL.getCode(), message);
    }

    public FailException(int code, String message) {
        super(code, message);
    }
}
