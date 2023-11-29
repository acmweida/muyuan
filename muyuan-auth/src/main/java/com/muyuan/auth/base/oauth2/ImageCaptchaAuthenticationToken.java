package com.muyuan.auth.base.oauth2;

import com.muyuan.auth.base.enums.CustomGrantType;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Getter
public class ImageCaptchaAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    private Object authentication;

    private String captcha;

    private String uuid;

    public ImageCaptchaAuthenticationToken(Authentication clientPrincipal, Map<String, Object> additionalParameters, String captcha, String uuid) {
        super(CustomGrantType.WEB_CLIENT_IMAGE_CAPTCHA.getGrantType(), clientPrincipal, additionalParameters);
        this.captcha = captcha;
        this.uuid = uuid;
        this.authentication = clientPrincipal;
        setAuthenticated(false);

    }

    public ImageCaptchaAuthenticationToken(Authentication authentication, Collection<? extends GrantedAuthority> grantedAuthorities) {
        super(CustomGrantType.WEB_CLIENT_IMAGE_CAPTCHA.getGrantType(), authentication, new HashMap<>());
        this.authentication = authentication;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }


}
