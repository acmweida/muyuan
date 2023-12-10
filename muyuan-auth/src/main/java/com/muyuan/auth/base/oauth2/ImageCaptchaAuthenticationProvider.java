package com.muyuan.auth.base.oauth2;

import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.auth.base.exception.ImageCaptchaException;
import com.muyuan.auth.dto.User;
import com.muyuan.auth.service.impl.UserServiceImpl;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * 自定义密码比较
 */
@Slf4j
public class ImageCaptchaAuthenticationProvider extends DaoAuthenticationProvider {

    protected final Log logger = LogFactory.getLog(getClass());
    private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();

    private UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    private boolean forcePrincipalAsString = false;

    protected boolean hideUserNotFoundExceptions = true;
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private UserCache userCache = new NullUserCache();

    private final UserServiceImpl userDetailsService;

    private final RedisTemplate<String,Object> redisTemplate;

    public ImageCaptchaAuthenticationProvider(UserServiceImpl userDetailsService,RedisTemplate<String,Object> redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ImageCaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private String determineUsername(Authentication authentication) {
        return (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(ImageCaptchaAuthenticationToken.class, authentication,
                () -> this.messages.getMessage("ImageCaptchaAuthenticationProvider.onlySupports",
                        "Only UsernamePasswordAuthenticationToken is supported"));

        String captchaInput = ((ImageCaptchaAuthenticationToken) authentication).getCaptcha();
        String uuid = ((ImageCaptchaAuthenticationToken) authentication).getUuid();

        if (ObjectUtils.isEmpty(uuid) || !redisTemplate.hasKey(GlobalConst.CAPTCHA_KEY_PREFIX + uuid)) {
            throw new ImageCaptchaException("验证码验证过期");
        }

        final Object captcha = redisTemplate.opsForValue().get(GlobalConst.CAPTCHA_KEY_PREFIX + uuid);
        assert captcha != null;
        if (!captcha.toString().equals(captchaInput)) {
            throw new ImageCaptchaException("验证码错误");
        }
        redisTemplate.delete(GlobalConst.CAPTCHA_KEY_PREFIX + uuid);

        String username = determineUsername(authentication);
        boolean cacheWasUsed = true;
        UserDetails user = this.userCache.getUserFromCache(username);
        if (user == null) {
            cacheWasUsed = false;
            try {
                user = retrieveUser(username, (ImageCaptchaAuthenticationToken) authentication);
            }
            catch (UsernameNotFoundException ex) {
                this.logger.debug("Failed to find user '" + username + "'");
                if (!this.hideUserNotFoundExceptions) {
                    throw ex;
                }
                throw new BadCredentialsException(this.messages
                        .getMessage("ImageCaptchaAuthenticationProvider.badCredentials", "Bad credentials"));
            }
            Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
        }
        try {
            this.preAuthenticationChecks.check(user);
            additionalAuthenticationChecks(user, (ImageCaptchaAuthenticationToken) authentication);
        }
        catch (AuthenticationException ex) {
            if (!cacheWasUsed) {
                throw ex;
            }
            // There was a problem, so try again after checking
            // we're using latest data (i.e. not from the cache)
            cacheWasUsed = false;
            user = retrieveUser(username, (ImageCaptchaAuthenticationToken) authentication);
            this.preAuthenticationChecks.check(user);
            additionalAuthenticationChecks(user, (ImageCaptchaAuthenticationToken) authentication);
        }
        this.postAuthenticationChecks.check(user);
        if (!cacheWasUsed) {
            this.userCache.putUserInCache(user);
        }
        Object principalToReturn = user;
        if (this.forcePrincipalAsString) {
            principalToReturn = user.getUsername();
        }
        return createSuccessAuthentication(principalToReturn, authentication, user);
    }

    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {
        // Ensure we return the original credentials the user supplied,
        // so subsequent attempts are successful even with encoded passwords.
        // Also ensure we return the original getDetails(), so that future
        // authentication events after cache expiry contain the details
        ImageCaptchaAuthenticationToken result = ImageCaptchaAuthenticationToken.authenticated(principal,
                authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        this.logger.debug("Authenticated user");
        return result;
    }


    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  ImageCaptchaAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages
                    .getMessage("ImageCaptchaAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String salt = "";
        String encryptKey = "";

        User user = (User) userDetails;
        salt = user.getSalt();
        encryptKey = user.getEncryptKey();

        String password = (String) authentication.getCredentials();
        final String presentedPassword = EncryptUtil.SHA1(password + salt, encryptKey);
        if (!userDetails.getPassword().equals(presentedPassword)) {
            log.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException(this.messages
                    .getMessage("ImageCaptchaAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

    protected final UserDetails retrieveUser(String username, ImageCaptchaAuthenticationToken authentication)
            throws AuthenticationException {

        try {
            String platformType =  authentication.getPlatformType();
            UserDetails loadedUser = this.userDetailsService.loadUserByUsername(username, PlatformType.valueOf(platformType));
//            UserDetails loadedUser = this.userDetailsService.loadUserByUsername(username,platformType);
            if (loadedUser == null) {
                throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
            }
            return loadedUser;
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            log.error("参数异常", e);
            throw new InternalAuthenticationServiceException(ResponseCode.ERROR.getMsg(), e);
        } catch (Exception ex) {
            log.error("认证异常", ex);
            throw new InternalAuthenticationServiceException(ResponseCode.ERROR.getMsg(), ex);
        }
    }

    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {

        @Override
        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                ImageCaptchaAuthenticationProvider.this.logger
                        .debug("Failed to authenticate since user account is locked");
                throw new LockedException(ImageCaptchaAuthenticationProvider.this.messages
                        .getMessage("ImageCaptchaAuthenticationProvider.locked", "User account is locked"));
            }
            if (!user.isEnabled()) {
                ImageCaptchaAuthenticationProvider.this.logger
                        .debug("Failed to authenticate since user account is disabled");
                throw new DisabledException(ImageCaptchaAuthenticationProvider.this.messages
                        .getMessage("ImageCaptchaAuthenticationProvider.disabled", "User is disabled"));
            }
            if (!user.isAccountNonExpired()) {
                ImageCaptchaAuthenticationProvider.this.logger
                        .debug("Failed to authenticate since user account has expired");
                throw new AccountExpiredException(ImageCaptchaAuthenticationProvider.this.messages
                        .getMessage("ImageCaptchaAuthenticationProvider.expired", "User account has expired"));
            }
        }

    }

    private class DefaultPostAuthenticationChecks implements UserDetailsChecker {

        @Override
        public void check(UserDetails user) {
            if (!user.isCredentialsNonExpired()) {
                ImageCaptchaAuthenticationProvider.this.logger
                        .debug("Failed to authenticate since user account credentials have expired");
                throw new CredentialsExpiredException(ImageCaptchaAuthenticationProvider.this.messages
                        .getMessage("ImageCaptchaAuthenticationProvider.credentialsExpired",
                                "User credentials have expired"));
            }
        }

    }

}
