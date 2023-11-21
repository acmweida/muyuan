//package com.muyuan.auth.base.config;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
///**
// * @ClassName CustomClientCredentialsTokenEndpointFilter
// * Description 客户端认证
// * @Author 2456910384
// * @Date 2022/1/4 14:15
// * @Version 1.0
// */
//public class CustomClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {
//    private final AuthorizationServerSecurityConfigurer configurer;
//
//    private AuthenticationEntryPoint authenticationEntryPoint;
//
//    public CustomClientCredentialsTokenEndpointFilter(AuthorizationServerSecurityConfigurer configurer) {
//        this.configurer = configurer;
//    }
//
//    @Override
//    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
//        super.setAuthenticationEntryPoint(null);
//        this.authenticationEntryPoint = authenticationEntryPoint;
//    }
//
//    @Override
//    protected AuthenticationManager getAuthenticationManager() {
//        return configurer.and().getSharedObject(AuthenticationManager.class);
//    }
//
//    @Override
//    public void afterPropertiesSet() {
//        setAuthenticationFailureHandler((request, response, e) -> authenticationEntryPoint.commence(request, response, e));
//        setAuthenticationSuccessHandler((request, response, authentication) -> {
//        });
//    }
//}
