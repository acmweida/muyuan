package com.muyuan.auth.base.login;

import com.muyuan.auth.base.exception.CaptchaMatchFailException;
import com.muyuan.auth.base.exception.CaptchaNotFoundException;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.exception.ArgumentException;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ImageCaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final RedisTemplate<String,Object> redisTemplate;
    public static final String CODE = "code";
    public static final String UUID = "uuid";
    public static final String PLATFORM_TYPE = "platformType";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    public ImageCaptchaAuthenticationFilter(RedisTemplate<String, Object> redisTemplate) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.redisTemplate = redisTemplate;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {


        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            sb.append(inputStr);
        }

        HashMap parameters = JSONUtil.parseObject(sb.toString(), HashMap.class);

        // captcha (REQUIRED)
        assert parameters != null;
        String code = (String) parameters.get(CODE);
        if (!StringUtils.hasText(code)) {
            throw new ArgumentException(StrUtil.format("参数：{}为空",CODE));
        }

        // uuid (REQUIRED)
        String uuid = (String) parameters.get(UUID);
        if (!StringUtils.hasText(uuid)) {
            throw new ArgumentException(StrUtil.format("参数：{}为空",UUID));
        }

        if (ObjectUtils.isEmpty(uuid) || Boolean.FALSE.equals(redisTemplate.hasKey(GlobalConst.CAPTCHA_KEY_PREFIX + uuid))) {
            throw new CaptchaNotFoundException("验证码验证过期");
        }

        final Object captcha = redisTemplate.opsForValue().get(GlobalConst.CAPTCHA_KEY_PREFIX + uuid);
        assert captcha != null;
        if (!captcha.toString().equals(code)) {
            throw new CaptchaMatchFailException("验证码错误");
        }
        redisTemplate.delete(GlobalConst.CAPTCHA_KEY_PREFIX + uuid);

        // uuid (REQUIRED)
        String platformType = (String) parameters.get(PLATFORM_TYPE);
        if (!StringUtils.hasText(platformType)) {
            throw new ArgumentException(StrUtil.format("字段:{} 不支持：{}",PLATFORM_TYPE,platformType));
        }

        // uuid (REQUIRED)
        String username = (String) parameters.get(USERNAME);
        if (!StringUtils.hasText(platformType) ) {
            throw new ArgumentException(StrUtil.format("参数：{}为空",USERNAME));
        }

        // uuid (REQUIRED)
        String password = (String) parameters.get(PASSWORD);
        if (!StringUtils.hasText(platformType) ) {
            throw new ArgumentException(StrUtil.format("参数：{}为空",PASSWORD));
        }

        ImageCaptchaAuthenticationToken authRequest = ImageCaptchaAuthenticationToken.unauthenticated(username,
                password,platformType);
        // Allow subclasses to set the "details" property
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
