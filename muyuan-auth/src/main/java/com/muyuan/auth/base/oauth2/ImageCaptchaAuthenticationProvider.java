package com.muyuan.auth.base.oauth2;

import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.auth.base.exception.ImageCaptchaException;
import com.muyuan.auth.dto.User;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * 自定义密码比较
 */
@Slf4j
public class ImageCaptchaAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final RedisTemplate<String,Object> redisTemplate;

    public ImageCaptchaAuthenticationProvider(UserDetailsService userDetailsService,RedisTemplate<String,Object> redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ImageCaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Map<String, String> parameters = ((ImageCaptchaAuthenticationToken) authentication).getDetails();

        String captchaInput = parameters.get("captcha");
        String uuid = parameters.get("uuid");

        if (ObjectUtils.isEmpty(uuid) || !redisTemplate.hasKey(GlobalConst.CAPTCHA_KEY_PREFIX + uuid)) {
            throw new ImageCaptchaException("验证码验证过期");
        }

        final Object captcha = redisTemplate.opsForValue().get(GlobalConst.CAPTCHA_KEY_PREFIX + uuid);
        assert captcha != null;
        if (!captcha.toString().equals(captchaInput)) {
            throw new ImageCaptchaException("验证码错误");
        }
        redisTemplate.delete(GlobalConst.CAPTCHA_KEY_PREFIX + uuid);

        return super.authenticate(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
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

    @Override
    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        try {
            Map<String, String> detial = ((ImageCaptchaAuthenticationToken) authentication).getDetails();
            String platformType = detial.get("platform_type");
//            UserDetails loadedUser = this.userDetailsService.loadUserByUsername(username, PlatformType.valueOf(platformType));
            UserDetails loadedUser = this.userDetailsService.loadUserByUsername(username);
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


}
