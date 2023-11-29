package com.muyuan.auth.base.enums;

import lombok.Getter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Getter
public enum CustomGrantType {

    WEB_CLIENT_IMAGE_CAPTCHA("WEB_CLIENT","image_captcha");

    private final String clientId;

    private final AuthorizationGrantType grantType;

    CustomGrantType(String clientId, String grantType) {
        this.clientId = clientId;
        this.grantType = new AuthorizationGrantType(grantType);
    }
}
