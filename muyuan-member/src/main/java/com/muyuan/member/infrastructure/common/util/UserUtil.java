package com.muyuan.member.infrastructure.common.util;

import com.muyuan.common.constant.auth.SecurityConstants;
import com.muyuan.common.util.JSONUtil;
import com.muyuan.member.interfaces.dto.UserDTO;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class UserUtil {

    private UserDTO getUserInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        RequestAttributes requestAttributes1 = RequestContextHolder.getRequestAttributes();
        String userStr = (String) requestAttributes1.getAttribute(SecurityConstants.REQUEST_USER_PARAM, RequestAttributes.SCOPE_REQUEST);
        UserDTO user = JSONUtil.parseObject(userStr, UserDTO.class);
        return user;
    }
}
