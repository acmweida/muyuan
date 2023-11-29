package com.muyuan.auth.base.oauth2;

import com.muyuan.auth.base.enums.CustomGrantType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ImageCaptchaAuthenticationConverter implements AuthenticationConverter {

    private static final String CAPTCHA = "captcha";
    private static final String UUID = "uuid";


    public ImageCaptchaAuthenticationConverter() {}

    @Override
    public Authentication convert(HttpServletRequest request) {

        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!CustomGrantType.WEB_CLIENT_IMAGE_CAPTCHA.getGrantType().getValue().equals(grantType)) {
            return null;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
        parameterMap.forEach((key, values) -> {
            if (values.length > 0) {
                for (String value : values) {
                    parameters.add(key, value);
                }
            }
        });

        // captcha (REQUIRED)
        String captcha = parameters.getFirst(CAPTCHA);
        if (!StringUtils.hasText(captcha) ||
                parameters.get(CAPTCHA).size() != 1) {
            throw new OAuth2AuthenticationException(CAPTCHA);
        }

        // uuid (REQUIRED)
        String uuid = parameters.getFirst(UUID);
        if (!StringUtils.hasText(captcha) ||
                parameters.get(UUID).size() != 1) {
            throw new OAuth2AuthenticationException(UUID);
        }

        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) &&
                    !key.equals(OAuth2ParameterNames.CLIENT_ID) &&
                    !key.equals(CAPTCHA)) {
                additionalParameters.put(key, value.get(0));
            }
        });

        ImageCaptchaAuthenticationToken imageCaptchaAuthenticationToken = new ImageCaptchaAuthenticationToken(
                authentication.getPrincipal(), authentication.getPrincipal(), captcha, uuid);
        imageCaptchaAuthenticationToken.setDetails(additionalParameters);

        return  imageCaptchaAuthenticationToken;
    }
}
