package com.muyuan.auth.base.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class ImageCaptchaAuthenticationConverter implements AuthenticationConverter {

    private static String GRANT_TYPE = "image_captcha";

    private RedisTemplate redisTemplate;

    public ImageCaptchaAuthenticationConverter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {

        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!GRANT_TYPE.equals(grantType)) {
            return null;
        }
        return null;

//        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
//
//        MultiValueMap<String, String> parameters = getParameters(request);
//
//        // code (REQUIRED)
//        String code = parameters.getFirst(OAuth2ParameterNames.CODE);
//        if (!StringUtils.hasText(code) ||
//                parameters.get(OAuth2ParameterNames.CODE).size() != 1) {
//            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
//        }
//
//        Map<String, Object> additionalParameters = new HashMap<>();
//        parameters.forEach((key, value) -> {
//            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) &&
//                    !key.equals(OAuth2ParameterNames.CLIENT_ID) &&
//                    !key.equals(OAuth2ParameterNames.CODE)) {
//                additionalParameters.put(key, value.get(0));
//            }
//        });
//
////        return new CustomCodeGrantAuthenticationToken(code, clientPrincipal, additionalParameters);
//
//
//        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
//        String username = parameters.get("username");
//        String password = parameters.get("password");
//        String captchaInput = parameters.get("captcha");
//        String uuid = parameters.get("uuid");
//        // Protect from downstream leaks of password
//        parameters.remove("password");
//
//        if (ObjectUtils.isEmpty(uuid) || !redisTemplate.hasKey(GlobalConst.CAPTCHA_KEY_PREFIX+uuid)) {
//            throw  new  ImageCaptchaException("验证码验证失败");
//        }
//
//        final Object captcha = redisTemplate.opsForValue().get(GlobalConst.CAPTCHA_KEY_PREFIX+uuid);
//        if (!captcha.toString().equals(captchaInput)) {
//            throw new ImageCaptchaException("验证码错误");
//        }
//        redisTemplate.delete(GlobalConst.CAPTCHA_KEY_PREFIX+uuid);
//
//
//        Authentication userAuth = new ImageCaptchaAuthenticationToken(username, password);
//        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
//        try {
//            userAuth = authenticationManager.authenticate(userAuth);
//        }
//        catch (AccountStatusException ase) {
//            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
//            throw new InvalidGrantException(ase.getMessage());
//        }
//        catch (BadCredentialsException e) {
//            // If the username/password are wrong the spec says we should send 400/invalid grant
//            throw new InvalidGrantException(e.getMessage());
//        }
//        if (userAuth == null || !userAuth.isAuthenticated()) {
//            throw new InvalidGrantException("Could not authenticate user: " + username);
//        }
//
//        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
//        return new ImageCaptchaAuthenticationToken(storedOAuth2Request, userAuth);
    }
}
