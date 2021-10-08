package com.muyuan.auth.base.granter;

import com.muyuan.auth.base.authenticationtoken.ImageCaptchaAuthenticationToken;
import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.auth.base.exception.ImageCaptchaException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ImageCaptchaTokenGranter extends AbstractTokenGranter {

    private static String GRANT_TYPE = "image_captcha";

    private final AuthenticationManager authenticationManager;

    private RedisTemplate redisTemplate;

    public ImageCaptchaTokenGranter(AuthenticationManager authenticationManager,
                                             RedisTemplate redisTemplate,
                                             AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.redisTemplate = redisTemplate;
    }

    protected ImageCaptchaTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                                                ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String account = parameters.get("account");
        String password = parameters.get("password");
        String captchaInput = parameters.get("captcha");
        String uuid = parameters.get("uuid");
        // Protect from downstream leaks of password
        parameters.remove("password");

        if (ObjectUtils.isEmpty(uuid) || !redisTemplate.hasKey(LoginMessageConst.CAPTCHA_KEY_PREFIX+uuid)) {
            throw  new  ImageCaptchaException("验证码验证失败");
        }

        final Object captcha = redisTemplate.opsForValue().get(LoginMessageConst.CAPTCHA_KEY_PREFIX+uuid);
        redisTemplate.delete(LoginMessageConst.CAPTCHA_KEY_PREFIX+uuid);
        if (!captcha.toString().equals(captchaInput)) {
            throw new ImageCaptchaException("验证码错误");
        }

        Authentication userAuth = new ImageCaptchaAuthenticationToken(account, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        }
        catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }
        catch (BadCredentialsException e) {
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + account);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
