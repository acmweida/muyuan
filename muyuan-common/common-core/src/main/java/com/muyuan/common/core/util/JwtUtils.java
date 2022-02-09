package com.muyuan.common.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.muyuan.common.core.constant.auth.SecurityConst;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
            throw new FileNotFoundException("请传入认证头");
        }
        JsonNode jsonNode = JSONUtil.readTree(URLDecoder.decode(payload,StandardCharsets.UTF_8.name()));
        return jsonNode;
    }

    /**
     * 解析JWT获取用户ID
     *
     * @return
     */
    public static Long getUserId() {
        Long id =   getJwtPayload().get(SecurityConst.USER_ID_KEY).asLong();
        return id;
    }

    /**
     * 解析JWT获取获取用户名
     *
     * @return
     */
    public static String getUsername() {
        String username = getJwtPayload().get(SecurityConst.USER_NAME_KEY).asText();
        return username;
    }



    /**
     * JWT获取用户角色列表
     *
     * @return 角色列表
     */
    public static List<String> getRoles() {
        List<String> roles = null;
        JsonNode jsonNode = getJwtPayload();
        if (jsonNode.has(SecurityConst.JWT_AUTHORITIES_KEY) && jsonNode.get(SecurityConst.JWT_AUTHORITIES_KEY).isArray()) {
            roles = jsonNode.findValuesAsText(SecurityConst.JWT_AUTHORITIES_KEY);;
        }
        return roles;
    }
}
