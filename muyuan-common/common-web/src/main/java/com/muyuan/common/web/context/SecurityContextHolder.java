package com.muyuan.common.web.context;

import com.fasterxml.jackson.databind.JsonNode;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.util.Convert;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.web.util.JwtUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取当前线程变量中的 用户id、用户名称、Token等信息
 * 注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 *
 * @author ruoyi
 */
public class SecurityContextHolder {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value == null ? StrUtil.EMPTY : value);
    }

    public static String get(String key) {
        Map<String, Object> map = getLocalMap();
        return Convert.toStr(map.getOrDefault(key, StrUtil.EMPTY));
    }

    public static <T> T get(String key, Class<T> clazz) {
        Map<String, Object> map = getLocalMap();
        return StrUtil.cast(map.getOrDefault(key, null));
    }

    public static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<String, Object>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static String getUserName() {
        String userName = get(SecurityConst.DETAILS_USERNAME,String.class);
        if (null == userName) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            userName = jwtPayLoad.get(SecurityConst.DETAILS_USERNAME).asText();
            set(SecurityConst.DETAILS_USERNAME,userName);
        }
        return userName;
    }

    public static String getUserKey() {
        return get(SecurityConst.USER_KEY);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    // jwt info
    public static List<String> getRoles() {
        List<String> roles = get(SecurityConst.USER_ROLE, ArrayList.class);
        if (null == roles) {
            roles = new ArrayList<>();
            JsonNode jwtPayLoad = getJwtPayLoad();
            if (jwtPayLoad.has(SecurityConst.JWT_AUTHORITIES_KEY) && jwtPayLoad.get(SecurityConst.JWT_AUTHORITIES_KEY).isArray()) {
                for (JsonNode node : jwtPayLoad.get(SecurityConst.JWT_AUTHORITIES_KEY)) {
                    roles.add(node.asText().replace(SecurityConst.AUTHORITY_PREFIX,""));
                }
            }
            set(SecurityConst.USER_ROLE,roles);
        }
        return roles;
    }

    public static boolean isLogin() {
        return JwtUtils.hasJwtPayload();
    }

    private static JsonNode getJwtPayLoad() {
        JsonNode jwtPayLoad = get(SecurityConst.JWT_PAYLOAD_KEY, JsonNode.class);
        if (null == jwtPayLoad) {
            jwtPayLoad = JwtUtils.getJwtPayload();
            set(SecurityConst.JWT_PAYLOAD_KEY, jwtPayLoad);
        }
        return jwtPayLoad;
    }

    public static Long getUserId() {
        Long userId = get(SecurityConst.DETAILS_USER_ID,Long.class);
        if (null == userId) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            userId = jwtPayLoad.get(SecurityConst.USER_ID_KEY).asLong();
            set(SecurityConst.DETAILS_USER_ID,userId);
        }
        return userId;
    }

    public static Long getShopId() {
        Long shopId = get(SecurityConst.DETAILS_SHOP_ID,Long.class);
        if (null == shopId) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            shopId = jwtPayLoad.get(SecurityConst.SHOP_ID_KEY).asLong();
            set(SecurityConst.DETAILS_SHOP_ID,shopId);
        }
        return shopId;
    }

    public static String getUserType() {
        String userType = get(SecurityConst.USER_TYPE);
        if (userType.isEmpty()) {
            JsonNode jwtPayLoad = getJwtPayLoad();
            userType = jwtPayLoad.get(SecurityConst.USER_TYPE).asText();
            set(SecurityConst.USER_TYPE,userType);
        }
        return userType;
    }
}
