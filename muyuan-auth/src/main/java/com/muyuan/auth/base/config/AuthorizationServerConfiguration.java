package com.muyuan.auth.base.config;

import com.muyuan.auth.base.exception.CustomWebResponseExceptionTranslator;
import com.muyuan.auth.base.granter.ImageCaptchaTokenGranter;
import com.muyuan.auth.dto.SysUserInfo;
import com.muyuan.auth.dto.UserInfo;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.enums.UserType;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.*;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(new BCryptPasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return  "{bcrypt}"+super.encode(rawPassword);
            }
        });
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 获取原有默认授权模式(授权码模式、密码模式、客户端模式、简化模式)的授权者
        List<TokenGranter> granterList = new ArrayList<>(Arrays.asList(endpoints.getTokenGranter()));

        // 添加验证码授权模式授权者
        granterList.add(new ImageCaptchaTokenGranter(authenticationManager,redisTemplate,endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()
        ));

        CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granterList);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter())
                .tokenGranter(compositeTokenGranter)
                .exceptionTranslator(new CustomWebResponseExceptionTranslator())
                .reuseRefreshTokens(true)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter(security);
        endpointFilter.afterPropertiesSet();
        endpointFilter.setAuthenticationEntryPoint(authenticationEntryPoint());
        security.addTokenEndpointAuthenticationFilter(endpointFilter);

        security
                .authenticationEntryPoint(authenticationEntryPoint())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            Result resultData = ResultUtil.error(ResponseCode.CLIENT_AUTHENTICATION_FAILED.getCode(), ResponseCode.CLIENT_AUTHENTICATION_FAILED.getMsg());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JSONUtil.toJsonString(resultData));
        };
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                Map<String,Object> info = new HashMap<>();
                if (authentication.getUserAuthentication().getPrincipal() instanceof UserInfo) {
                    UserInfo user = (UserInfo) (authentication.getUserAuthentication()).getPrincipal();
                    info.put(SecurityConst.USER_NAME_KEY,user.getUsername());
                    info.put(SecurityConst.USER_ID_KEY,user.getId());
                    info.put(SecurityConst.USER_TYPE, UserType.MEMBER);
                    info.put(SecurityConst.SHOP_ID_KEY, user.getShopId());
                } else {
                    SysUserInfo sysUserInfo = ((SysUserInfo) ( authentication.getUserAuthentication()).getPrincipal());
                    info.put(SecurityConst.USER_NAME_KEY,sysUserInfo.getUsername());
                    info.put(SecurityConst.USER_ID_KEY,sysUserInfo.getId());
                    info.put(SecurityConst.USER_TYPE, UserType.SYSUSER);
                    info.put(SecurityConst.SHOP_ID_KEY, "");
                }
                ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
                return super.enhance(accessToken, authentication);
            }
        };



        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }


    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

}
