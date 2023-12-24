package com.muyuan.auth.base.exception;

import com.muyuan.common.core.enums.ResponseCode;

import java.io.Serial;


public class CaptchaMatchFailException extends AuthException {


    @Serial
    private static final long serialVersionUID = -3500400932934196336L;

    public CaptchaMatchFailException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaMatchFailException(String msg) {
        super(msg);
    }

    @Override
    public ResponseCode getResponseCode() {
        return ResponseCode.CAPTCHA_MATCH_FAIL;
    }
}
