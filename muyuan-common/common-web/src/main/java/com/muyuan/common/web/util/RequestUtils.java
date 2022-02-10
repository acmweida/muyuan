package com.muyuan.common.web.util;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 请求工具类
 *
 * @author xianrui
 */
@Slf4j
public class RequestUtils {
    @SneakyThrows
    public static String getGrantType() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String grantType = request.getParameter(SecurityConst.GRANT_TYPE_KEY);
        return grantType;
    }


    /**
     * 获取登录认证的客户端ID
     * <p>
     * 兼容两种方式获取OAuth2客户端信息（client_id、client_secret）
     * 方式一：client_id、client_secret放在请求路径中
     * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
     *
     * @return
     */
    @SneakyThrows
    public static String getOAuth2ClientId() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 从请求路径中获取
        String clientId = request.getParameter(SecurityConst.CLIENT_ID_KEY);
        if (StrUtil.isNotBlank(clientId)) {
            return clientId;
        }

        // 从请求头获取
        String basic = request.getHeader(SecurityConst.AUTHORIZATION_KEY);
        if (StrUtil.isNotBlank(basic) && basic.startsWith(SecurityConst.BASIC_PREFIX)) {
            basic = basic.replace(SecurityConst.BASIC_PREFIX, StringUtils.EMPTY);
            String basicPlainText = new String(Base64.getDecoder().decode(basic.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            clientId = basicPlainText.split(":")[0]; //client:secret
        }
        return clientId;
    }

//    /**
//     * 解析JWT获取获取认证方式
//     *
//     * @return
//     */
//    @SneakyThrows
//    public static String getAuthenticationMethod() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String refreshToken = request.getParameter(SecurityConst.REFRESH_TOKEN_KEY);
//
//        String payload = StrUtil.toString(JWSObject.parse(refreshToken).getPayload());
//        Map jsonObject = JSONUtil.parseObject(payload, HashMap.class);
//
//        String authenticationMethod = MapUtils.getString(jsonObject,SecurityConst.AUTHENTICATION_METHOD);
//        if (StrUtil.isBlank(authenticationMethod)) {
//            authenticationMethod = AuthenticationMethodEnum.USERNAME.getValue();
//        }
//        return authenticationMethod;
//    }
}
