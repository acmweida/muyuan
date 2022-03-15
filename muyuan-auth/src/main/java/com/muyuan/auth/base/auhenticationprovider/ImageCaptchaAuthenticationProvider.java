package com.muyuan.auth.base.auhenticationprovider;

import com.muyuan.auth.base.authenticationtoken.ImageCaptchaAuthenticationToken;
import com.muyuan.auth.dto.SysUserInfo;
import com.muyuan.auth.dto.UserInfo;
import com.muyuan.auth.service.impl.UserServiceImpl;
import com.muyuan.common.core.util.EncryptUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 自定义密码比较
 */
public class ImageCaptchaAuthenticationProvider implements AuthenticationProvider {

    protected final Log logger = LogFactory.getLog(getClass());

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    protected boolean hideUserNotFoundExceptions = true;
    private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
    private UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    private UserServiceImpl userDetailsService;

    public UserServiceImpl getUserDetailsService() {
        return userDetailsService;
    }

    public ImageCaptchaAuthenticationProvider(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private String determineUsername(Authentication authentication) {
        return (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Assert.isInstanceOf(ImageCaptchaAuthenticationToken.class, authentication,
                () -> this.messages.getMessage("ImageCaptchaAuthenticationProvider.onlySupports",
                        "Only UsernamePasswordAuthenticationToken is supported"));
        String username = determineUsername(authentication);
        UserDetails user;
        try {
            user = retrieveUser(username, (ImageCaptchaAuthenticationToken) authentication);
        } catch (UsernameNotFoundException ex) {
            this.logger.debug("Failed to find user '" + username + "'");
            if (!this.hideUserNotFoundExceptions) {
                throw ex;
            }
            throw new BadCredentialsException(this.messages
                    .getMessage("ImageCaptchaAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
        try {
            this.preAuthenticationChecks.check(user);
            additionalAuthenticationChecks(user, (ImageCaptchaAuthenticationToken) authentication);
        } catch (AuthenticationException ex) {
            user = retrieveUser(username, (ImageCaptchaAuthenticationToken) authentication);
            this.preAuthenticationChecks.check(user);
            additionalAuthenticationChecks(user, (ImageCaptchaAuthenticationToken) authentication);
        }
        this.postAuthenticationChecks.check(user);
        Object principalToReturn = user;
        return createSuccessAuthentication(principalToReturn, authentication, user);

    }

    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {
        // Ensure we return the original credentials the user supplied,
        // so subsequent attempts are successful even with encoded passwords.
        // Also ensure we return the original getDetails(), so that future
        // authentication events after cache expiry contain the details
        ImageCaptchaAuthenticationToken result = new ImageCaptchaAuthenticationToken(principal,
                authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        this.logger.debug("Authenticated user");
        return result;
    }

    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  ImageCaptchaAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages
                    .getMessage("ImageCaptchaAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String salt = "";
        String encryptKey ="";
        // 商户用户
        if (userDetails instanceof UserInfo) {
            UserInfo userInfo = (UserInfo) userDetails;
            salt = userInfo.getSalt();
            encryptKey = userInfo.getEncryptKey();
            // 系统用户
        } else if (userDetails instanceof SysUserInfo) {
            SysUserInfo userInfo = (SysUserInfo) userDetails;
            salt = userInfo.getSalt();
            encryptKey = userInfo.getEncryptKey();
        }

        String password = (String) authentication.getCredentials();
        final String presentedPassword = EncryptUtil.SHA1(password + salt, encryptKey);
        if (!userDetails.getPassword().equals(presentedPassword)) {
            this.logger.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException(this.messages
                    .getMessage("ImageCaptchaAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ImageCaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }

    protected final UserDetails retrieveUser(String username, ImageCaptchaAuthenticationToken authentication)
            throws AuthenticationException {
        try {
            Map<String,String> detial = (Map<String, String>) authentication.getDetails();
            String userType = detial.get("user_type");
            UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username,userType);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        } catch (UsernameNotFoundException ex) {
            throw ex;
        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
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
