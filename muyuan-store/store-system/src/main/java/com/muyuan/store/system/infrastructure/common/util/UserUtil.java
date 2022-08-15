package com.muyuan.store.system.infrastructure.common.util;

import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.member.interfaces.to.UserTO;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class UserUtil {

    private UserTO getUserInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        RequestAttributes requestAttributes1 = RequestContextHolder.getRequestAttributes();
        String userStr = (String) requestAttributes1.getAttribute(SecurityConst.JWT_PAYLOAD_KEY, RequestAttributes.SCOPE_REQUEST);
        UserTO user = JSONUtil.parseObject(userStr, UserTO.class);
        return user;
    }
}
