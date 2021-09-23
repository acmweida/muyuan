package com.muyuan.auth.base.exception;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;

public class ImageCaptchaException extends InvalidGrantException {
    public ImageCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public ImageCaptchaException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "image_captch_error";
    }
}
