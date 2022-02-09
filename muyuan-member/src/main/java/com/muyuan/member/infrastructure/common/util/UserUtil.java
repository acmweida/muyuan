package com.muyuan.member.infrastructure.common.util;

import com.muyuan.common.core.constant.auth.SecurityConstants;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.member.interfaces.dto.UserDTO;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class UserUtil {

    private UserDTO getUserInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        RequestAttributes requestAttributes1 = RequestContextHolder.getRequestAttributes();
        String userStr = (String) requestAttributes1.getAttribute(SecurityConstants.JWT_PAYLOAD_KEY, RequestAttributes.SCOPE_REQUEST);
        UserDTO user = JSONUtil.parseObject(userStr, UserDTO.class);
        return user;
    }
}
