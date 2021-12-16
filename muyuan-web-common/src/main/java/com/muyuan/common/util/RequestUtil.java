package com.muyuan.common.util;

import com.muyuan.common.constant.CommonConst;
import com.muyuan.common.constant.auth.AuthConst;
import com.muyuan.common.enums.ResponseCode;
import com.muyuan.common.exception.handler.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
public class RequestUtil {

    public static long getCurrentUserId() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String userStr = request.getHeader(AuthConst.REQUEST_USER_PARAM);
        if (null == userStr) {
            log.error("exception code {} : not found user in request header",ResponseCode.ARGUMENT_EEORR.getCode());
            throw new UnAuthorizedException();
        }

        HashMap userJson = JSONUtil.parseObject(userStr, HashMap.class);
        if (!userJson.containsKey(CommonConst.ID)) {
            log.error("exception code {} :  user id not found in {}", ResponseCode.ARGUMENT_EEORR.getCode(),userJson);
            throw new UnAuthorizedException();
        }

        long id = (long)userJson.get(CommonConst.ID);

        return id;
    }
}
