package com.muyuan.common.web.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.exception.handler.UnAuthorizedException;
import com.muyuan.common.core.util.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * JWT工具类
 *
 * @author xianrui
 */
@Slf4j
public class JwtUtils {

    @SneakyThrows
    public static JsonNode getJwtPayload() {
        String payload = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(SecurityConst.JWT_PAYLOAD_KEY);
        if (null == payload) {
            throw new UnAuthorizedException();
        }
        JsonNode jsonNode = JSONUtil.readTree(URLDecoder.decode(payload,StandardCharsets.UTF_8.name()));
        return jsonNode;
    }

//    /**
//     * 解析JWT获取用户ID
//     *
//     * @return
//     */
//    public static Long getUserId() {
//        Long id =   getJwtPayload().get(SecurityConst.USER_ID_KEY).asLong();
//        return id;
//    }
//
//    /**
//     * 解析JWT获取获取用户名
//     *
//     * @return
//     */
//    public static String getUsername() {
//        String username = getJwtPayload().get(SecurityConst.USER_NAME_KEY).asText();
//        return username;
//    }
//
//    /**
//     * JWT获取用户角色列表
//     *
//     * @return 角色列表
//     */
//    public static List<String> getRoles() {
//        List<String> roles = new ArrayList<>();
//        JsonNode jsonNode = getJwtPayload();
//        if (jsonNode.has(SecurityConst.JWT_AUTHORITIES_KEY) && jsonNode.get(SecurityConst.JWT_AUTHORITIES_KEY).isArray()) {
//            for (JsonNode node :  jsonNode.get(SecurityConst.JWT_AUTHORITIES_KEY) ) {
//                roles.add(node.asText());
//            }
//        }
//        return roles;
//    }

}
