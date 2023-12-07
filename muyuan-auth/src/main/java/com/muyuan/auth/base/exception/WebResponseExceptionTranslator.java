package com.muyuan.auth.base.exception;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.ResultUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName CustomWebResponseExceptionTranslator
 * Description 异常处理器
 * @Author 2456910384
 * @Date 2022/1/26 16:56
 * @Version 1.0
 */
@Slf4j
public class WebResponseExceptionTranslator  implements AuthenticationEntryPoint, AccessDeniedHandler {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.error(authException.toString());
        Result result = null;
        if (ImageCaptchaException.class.isAssignableFrom(authException.getClass())) {
            result =  ResultUtil.error(ResponseCode.CAPTCHA_ERROR.getCode(),ResponseCode.CAPTCHA_ERROR.getMsg());
        } else {
            result =  ResultUtil.error(ResponseCode.AUTHORIZED_ERROR.getCode(),ResponseCode.AUTHORIZED_ERROR.getMsg());
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(Objects.requireNonNull(JSONUtil.toJsonString(new ResponseEntity(JSONUtil.toJsonString(result), headers, HttpStatus.OK))));
        response.getWriter().flush();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.error(accessDeniedException.toString());
        Result result = null;
        ImageCaptchaException.class.isAssignableFrom(accessDeniedException.getClass());
        result = ResultUtil.error(ResponseCode.AUTHORIZED_ERROR.getCode(), ResponseCode.AUTHORIZED_ERROR.getMsg());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(Objects.requireNonNull(JSONUtil.toJsonString(new ResponseEntity(JSONUtil.toJsonString(result), headers, HttpStatus.OK))));
        response.getWriter().flush();
    }
}
