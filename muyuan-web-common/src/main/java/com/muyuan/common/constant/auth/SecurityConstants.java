package com.muyuan.common.constant.auth;

public interface SecurityConstants {
     String AUTHORITY_PREFIX = "ROLE_";

    /**
     * 认证请求头key
     */
     String AUTHORIZATION_KEY = "Authorization";

    /**
     * JWT存储权限属性
     */
     String JWT_AUTHORITIES_KEY = "authorities";

     String REQUEST_USER_PARAM = "user";

     String ROOT_ROLE_CODE = "ADMIN";

    /**
     * JWT令牌前缀
     */
    String JWT_PREFIX = "Bearer ";

    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";

    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";

    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";
}
