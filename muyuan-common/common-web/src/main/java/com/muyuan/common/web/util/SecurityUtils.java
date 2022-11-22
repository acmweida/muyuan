package com.muyuan.common.web.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.exception.UnAuthorizedException;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.context.SecurityContextHolder;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 权限获取工具类
 *
 * @author
 */
public class SecurityUtils {


    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static String getUsername() {
        String userName = SecurityContextHolder.get(SecurityConst.DETAILS_USERNAME, String.class);
        if (null == userName) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            userName = jwtPayLoad.get(SecurityConst.DETAILS_USERNAME).asText();
            SecurityContextHolder.set(SecurityConst.DETAILS_USERNAME, userName);
        }
        return userName;
    }


    // jwt info
    public static List<String> getRoles() {
        List<String> roles = SecurityContextHolder.get(SecurityConst.USER_ROLE, ArrayList.class);
        if (null == roles) {
            roles = new ArrayList<>();
            JsonNode jwtPayLoad = getJwtPayLoad();
            if (jwtPayLoad.has(SecurityConst.JWT_AUTHORITIES_KEY) && jwtPayLoad.get(SecurityConst.JWT_AUTHORITIES_KEY).isArray()) {
                for (JsonNode node : jwtPayLoad.get(SecurityConst.JWT_AUTHORITIES_KEY)) {
                    roles.add(node.asText().replace(SecurityConst.AUTHORITY_PREFIX, ""));
                }
            }
            SecurityContextHolder.set(SecurityConst.USER_ROLE, roles);
        }
        return roles;
    }

    public static boolean isLogin() {
        return hasJwtPayload();
    }

    public static String getJwtPayLoadContext() {
        String jwtPayloadStr = null;
        if (hasJwtPayloadWeb()) {
            jwtPayloadStr = getJwtPayloadWeb();
        } else if (hasJwtPayloadRpc()) {
            jwtPayloadStr = getJwtPayloadRpc();
        }
        return jwtPayloadStr;
    }

    private static JsonNode getJwtPayLoad() {
        JsonNode jwtPayLoad = SecurityContextHolder.get(SecurityConst.JWT_PAYLOAD_KEY, JsonNode.class);
        if (null == jwtPayLoad) {
            String jwtPayloadStr = getJwtPayLoadContext();
            jwtPayLoad = getJwtPayload(jwtPayloadStr);
            SecurityContextHolder.set(SecurityConst.JWT_PAYLOAD_KEY, jwtPayLoad);
        }
        return jwtPayLoad;
    }

    @SneakyThrows
    private static JsonNode getJwtPayload(String payload) {
        if (null == payload) {
            throw new UnAuthorizedException();
        }
        JsonNode jsonNode = JSONUtil.readTree(URLDecoder.decode(payload, StandardCharsets.UTF_8.name()));
        return jsonNode;
    }

    public static void setJwtPayLoad(String jwtPayLoad) {
        SecurityContextHolder.remove();
        SecurityContextHolder.set(SecurityConst.JWT_PAYLOAD_KEY, getJwtPayload(jwtPayLoad));
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        Long userId = SecurityContextHolder.get(SecurityConst.DETAILS_USER_ID, Long.class);
        if (null == userId) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            userId = jwtPayLoad.get(SecurityConst.USER_ID_KEY).asLong();
            SecurityContextHolder.set(SecurityConst.DETAILS_USER_ID, userId);
        }
        return userId;
    }

    /**
     * 获取用户ID
     */
    public static Long getShopId() {
        Long shopId = SecurityContextHolder.get(SecurityConst.DETAILS_SHOP_ID, Long.class);
        if (null == shopId) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            shopId = jwtPayLoad.get(SecurityConst.SHOP_ID_KEY).asLong();
            SecurityContextHolder.set(SecurityConst.DETAILS_SHOP_ID, shopId);
        }
        return shopId;
    }


    /**
     * 获取用户ID
     */
    public static String getJtl() {
        String shopId = SecurityContextHolder.get(SecurityConst.JWT_JTI, String.class);
        if (null == shopId) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            shopId = jwtPayLoad.get(SecurityConst.JWT_JTI).asText();
            SecurityContextHolder.set(SecurityConst.JWT_JTI, shopId);
        }
        return shopId;
    }

    /**
     * 获取用户ID
     */
    public static Long getExpireTime() {
        Long shopId = SecurityContextHolder.get(SecurityConst.JWT_EXP, Long.class);
        if (null == shopId) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            shopId = jwtPayLoad.get(SecurityConst.JWT_EXP).asLong();
            SecurityContextHolder.set(SecurityConst.JWT_EXP, shopId);
        }
        return shopId;
    }


    public static PlatformType getPlatformType() {
        String userType = SecurityContextHolder.get(SecurityConst.PLATFORM_TYPE);
        if (StringUtils.isEmpty(userType)) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            userType = jwtPayLoad.get(SecurityConst.PLATFORM_TYPE).asText();
            SecurityContextHolder.set(SecurityConst.PLATFORM_TYPE, userType);
        }
        return PlatformType.valueOf(userType);
    }

    private static boolean hasJwtPayload() {
        return hasJwtPayloadWeb() || hasJwtPayloadRpc();
    }

    private static boolean hasJwtPayloadWeb() {
        return ObjectUtils.isNotEmpty(RequestContextHolder.getRequestAttributes())
                && ObjectUtils.isNotEmpty(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest())
                && ObjectUtils.isNotEmpty(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(SecurityConst.JWT_PAYLOAD_KEY));
    }

    private static boolean hasJwtPayloadRpc() {
        return ObjectUtils.isNotEmpty(getJwtPayloadRpc());
    }


    private static String getJwtPayloadRpc() {
        return RpcContext.getContext().getAttachment(SecurityConst.JWT_PAYLOAD_KEY);
    }

    private static String getJwtPayloadWeb() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getHeader(SecurityConst.JWT_PAYLOAD_KEY);
    }


}
