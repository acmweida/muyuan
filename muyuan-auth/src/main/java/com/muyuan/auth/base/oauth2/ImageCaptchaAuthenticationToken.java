package com.muyuan.auth.base.oauth2;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serial;
import java.util.Collection;


@Getter
public class ImageCaptchaAuthenticationToken extends UsernamePasswordAuthenticationToken {

    @Serial
    private static final long serialVersionUID = -4040750678873008922L;

    private String captcha;

    private String uuid;

    private String platformType;

    public ImageCaptchaAuthenticationToken(Object principal, Object credentials,String captcha, String uuid,String platformType) {
        super(principal, credentials);
        this.captcha = captcha;
        this.uuid = uuid;
        this.platformType = platformType;
        setAuthenticated(false);
    }

    public ImageCaptchaAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> grantedAuthorities) {
        super(principal,credentials,grantedAuthorities);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

}
