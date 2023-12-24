package com.muyuan.auth.base.exception;


import com.muyuan.common.core.enums.ResponseCode;
import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public abstract class AuthException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 7849751734785570083L;

    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthException(String msg) {
        super(msg);
    }


    public abstract ResponseCode getResponseCode();

}
