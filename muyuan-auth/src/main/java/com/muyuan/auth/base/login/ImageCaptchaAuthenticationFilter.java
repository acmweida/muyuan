package com.muyuan.auth.base.login;

import com.muyuan.auth.base.exception.CaptchaMatchFailException;
import com.muyuan.auth.base.exception.CaptchaNotFoundException;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.exception.ArgumentException;
import com.muyuan.common.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

public class ImageCaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final RedisTemplate<String,Object> redisTemplate;
    public static final String CAPTCHA = "captcha";
    public static final String UUID = "uuid";
    public static final String PLATFORM_TYPE = "platformType";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    public ImageCaptchaAuthenticationFilter(RedisTemplate<String, Object> redisTemplate) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.redisTemplate = redisTemplate;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{

        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
        parameterMap.forEach((key, values) -> {
            for (String value : values) {
                parameters.add(key, value);
            }
        });

        // captcha (REQUIRED)
        String captchaInput = parameters.getFirst(CAPTCHA);
        if (!StringUtils.hasText(captchaInput) ||
                Objects.requireNonNull(parameters.get(CAPTCHA)).size() != 1) {
            throw new ArgumentException(StrUtil.format("参数：{}为空",CAPTCHA));
        }

        // uuid (REQUIRED)
        String uuid = parameters.getFirst(UUID);
        if (!StringUtils.hasText(uuid) ||
                Objects.requireNonNull(parameters.get(UUID)).size() != 1) {
            throw new ArgumentException(StrUtil.format("参数：{}为空",UUID));
        }

        if (ObjectUtils.isEmpty(uuid) || Boolean.FALSE.equals(redisTemplate.hasKey(GlobalConst.CAPTCHA_KEY_PREFIX + uuid))) {
            throw new CaptchaNotFoundException("验证码验证过期");
        }

        final Object captcha = redisTemplate.opsForValue().get(GlobalConst.CAPTCHA_KEY_PREFIX + uuid);
        assert captcha != null;
        if (!captcha.toString().equals(captchaInput)) {
            throw new CaptchaMatchFailException("验证码错误");
        }
//        redisTemplate.delete(GlobalConst.CAPTCHA_KEY_PREFIX + uuid);

        // uuid (REQUIRED)
        String platformType = parameters.getFirst(PLATFORM_TYPE);
        if (!StringUtils.hasText(platformType) ||
                Objects.requireNonNull(parameters.get(PLATFORM_TYPE)).size() != 1) {
            throw new ArgumentException(StrUtil.format("字段:{} 不支持：{}",PLATFORM_TYPE,platformType));
        }

        // uuid (REQUIRED)
        String username = parameters.getFirst(USERNAME);
        if (!StringUtils.hasText(platformType) ||
                Objects.requireNonNull(parameters.get(USERNAME)).size() != 1) {
            throw new ArgumentException(StrUtil.format("参数：{}为空",USERNAME));
        }

        // uuid (REQUIRED)
        String password = parameters.getFirst(PASSWORD);
        if (!StringUtils.hasText(platformType) ||
                Objects.requireNonNull(parameters.get(PASSWORD)).size() != 1) {
            throw new ArgumentException(StrUtil.format("参数：{}为空",PASSWORD));
        }

        ImageCaptchaAuthenticationToken authRequest = ImageCaptchaAuthenticationToken.unauthenticated(username,
                password,platformType);
        // Allow subclasses to set the "details" property
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
