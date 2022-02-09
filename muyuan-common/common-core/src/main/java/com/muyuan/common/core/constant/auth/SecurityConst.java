package com.muyuan.common.core.constant.auth;

public interface SecurityConst {

     String AUTHORITY_PREFIX = "ROLE_";

    /**
     * 认证请求头key
     */
     String AUTHORIZATION_KEY = "Authorization";

    /**
     * JWT存储权限属性
     */
     String JWT_AUTHORITIES_KEY = "authorities";

     String REQUEST_USER_PARAM = "username";

     String ROOT_ROLE_CODE = "ADMIN";

     String SHOP_KEEPER_ROLE_CODE = "SHOP_KEEPER";

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

    String USER_ID_KEY = "userId";

    String USER_NAME_KEY = "username";

    String GRANT_TYPE_KEY = "grant_type";

    String CLIENT_ID_KEY = "client_id";

    /**
     * Basic认证前缀
     */
    String BASIC_PREFIX = "Basic ";

    String REFRESH_TOKEN_KEY = "refresh_token";

    /**
     * 认证方式
     */
    String AUTHENTICATION_METHOD = "authenticationMethod";

    /**
     * JWT ID 唯一标识
     */
    String JWT_EXP = "exp";
}
