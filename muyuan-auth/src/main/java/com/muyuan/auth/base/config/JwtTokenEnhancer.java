package com.muyuan.auth.base.config;

import com.muyuan.auth.dto.SysUserInfo;
import com.muyuan.auth.dto.UserInfo;
import com.muyuan.common.core.constant.auth.SecurityConst;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT token内容增强器
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String,Object> info = new HashMap<>();
        if (authentication.getUserAuthentication() instanceof UserInfo) {
            info.put(SecurityConst.USER_NAME_KEY,((UserInfo) ( authentication.getUserAuthentication()).getPrincipal()).getUsername());
            info.put(SecurityConst.USER_ID_KEY,((UserInfo) ( authentication.getUserAuthentication()).getPrincipal()).getId());
            info.put(SecurityConst.USER_PERMISSIONS_KEY,((UserInfo) ( authentication.getUserAuthentication()).getPrincipal()).getPermissions());
        } else {
            info.put(SecurityConst.USER_NAME_KEY,((SysUserInfo) ( authentication.getUserAuthentication()).getPrincipal()).getUsername());
            info.put(SecurityConst.USER_ID_KEY,((SysUserInfo) ( authentication.getUserAuthentication()).getPrincipal()).getId());
            info.put(SecurityConst.USER_PERMISSIONS_KEY,((SysUserInfo) ( authentication.getUserAuthentication()).getPrincipal()).getPermissions());
        }
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
