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

    String ADMIN_ROOT_ROLE_CODE = "ADMIN";

    String SHOP_KEEPER_ROLE_CODE = "SHOP_KEEPER";

//    /**
//     * 默认角色
//     */
//    String DEFAULT_ROLE = AUTHORITY_PREFIX+"DEFAULT";

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

    String USER_PERMISSIONS_KEY = "permissions";

    String GRANT_TYPE_KEY = "grant_type";

    String CLIENT_ID_KEY = "client_id";

    String TOKEN_KEY = "access_token";

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

    /**
     * 用户ID字段
     */
    String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    String DETAILS_USERNAME = "username";

    /**
     * 授权信息字段
     */
    String AUTHORIZATION_HEADER = "authorization";

    /**
     * 请求来源
     */
    String FROM_SOURCE = "from-source";

    /**
     * 内部请求
     */
    String INNER = "inner";

    /**
     * 用户标识
     */
    String USER_KEY = "user_key";

    /**
     * 登录用户
     */
    String LOGIN_USER = "login_user";

    String ALL_PERMISSION = "*:*:*";

    String USER_ROLE = "user_role";

    String USER_TYPE = "user_type";
}
