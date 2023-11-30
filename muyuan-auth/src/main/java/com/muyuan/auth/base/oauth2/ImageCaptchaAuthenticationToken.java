package com.muyuan.auth.base.oauth2;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serial;
import java.util.Collection;
import java.util.Map;


@Getter
public class ImageCaptchaAuthenticationToken extends UsernamePasswordAuthenticationToken {

    @Serial
    private static final long serialVersionUID = -4040750678873008922L;

    private String captcha;

    private String uuid;

    private Map<String,String> details;

    public ImageCaptchaAuthenticationToken(Object principal, Object credentials,String captcha, String uuid) {
        super(principal, credentials);
        this.captcha = captcha;
        this.uuid = uuid;
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

    @Override
    public Map<String,String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
