package com.muyuan.auth.base.login;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serial;
import java.util.Collection;


@Getter
public class ImageCaptchaAuthenticationToken extends AbstractAuthenticationToken {

    @Serial
    private static final long serialVersionUID = -4040750678873008922L;

    private String platformType;

    private final Object principal;

    private final Object credentials;

    public ImageCaptchaAuthenticationToken(Object principal, Object credentials,String platformType) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.platformType = platformType;
        setAuthenticated(false);
    }

    public ImageCaptchaAuthenticationToken(Object principal, Object credentials, String platformType,Collection<? extends GrantedAuthority> grantedAuthorities) {
        super(grantedAuthorities);
        this.principal = principal;
        this.credentials =credentials;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    public static ImageCaptchaAuthenticationToken unauthenticated(Object principal, Object credentials,String platformType) {
        return new ImageCaptchaAuthenticationToken(principal,  credentials,platformType);
    }

    public static ImageCaptchaAuthenticationToken authenticated(Object principal, Object credentials,String platformType,
                                                                    Collection<? extends GrantedAuthority> authorities) {
        return new ImageCaptchaAuthenticationToken(principal,  credentials, platformType,authorities);
    }

}
