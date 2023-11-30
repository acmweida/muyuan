package com.muyuan.auth.base.exception;


import org.springframework.security.core.AuthenticationException;

public class ImageCaptchaException extends AuthenticationException {
    public ImageCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public ImageCaptchaException(String msg) {
        super(msg);
    }
}
