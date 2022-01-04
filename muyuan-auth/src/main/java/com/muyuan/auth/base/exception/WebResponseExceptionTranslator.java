package com.muyuan.auth.base.exception;

import com.muyuan.common.enums.ResponseCode;
import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;

public class WebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
    
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        if (e instanceof OAuth2Exception) {
            Result result = new Result();
            result.setCode(ResponseCode.AUTHORIZED_FAULT.getCode());
            if (e instanceof BadClientCredentialsException) {
                result.setMsg("客户端信息错误！");
            }
            return ResponseEntity.ok(result);
        }

       return super.translate(e);

    }
}
