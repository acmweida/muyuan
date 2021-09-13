package com.muyuan.auth.base.auhenticationprovider;

import com.muyuan.auth.base.authenticationtoken.ImageCaptchaAuthenticationToken;
import com.muyuan.auth.dto.UserInfo;
import com.muyuan.common.util.EncryptUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ImageCaptchaAuthenticationProvider implements AuthenticationProvider {



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ImageCaptchaAuthenticationToken token =   (ImageCaptchaAuthenticationToken) authentication;
        String account = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
//        UserDetails userFromCache = getUserCache().getUserFromCache(account);
//        UserInfo userDetails = (UserInfo) getUserDetailsService().loadUserByUsername(account);
//        final String presentedPassword = EncryptUtil.SHA1(password + userDetails.getSalt(), userDetails.getEncryptKey());

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ImageCaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }




}
