package com.muyuan.member.infrastructure.common.util;

import com.alibaba.fastjson.JSONObject;
import com.muyuan.common.constant.auth.AuthConst;
import com.muyuan.member.interfaces.dto.UserDTO;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class UserUtil {

    private UserDTO getUserInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        RequestAttributes requestAttributes1 = RequestContextHolder.getRequestAttributes();
        String userStr = (String) requestAttributes1.getAttribute(AuthConst.REQUEST_USER_PARAM, RequestAttributes.SCOPE_REQUEST);
        UserDTO user = JSONObject.parseObject(userStr, UserDTO.class);
        return user;
    }
}
