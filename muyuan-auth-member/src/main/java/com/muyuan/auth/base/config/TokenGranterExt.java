package com.muyuan.auth.base.config;

import com.muyuan.auth.base.granter.ImageCaptchaTokenGranter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TokenGranterExt {

    public static TokenGranter getTokenGranter(final AuthenticationManager authenticationManager,
                                               final AuthorizationServerEndpointsConfigurer endpointsConfigurer,
                                               RedisTemplate redisTemplate
    ) {

        //  默认tokenGranter集合 security 自带的
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpointsConfigurer.getTokenGranter()));
        //添加验证码
        granters.add(new ImageCaptchaTokenGranter(authenticationManager,  redisTemplate, endpointsConfigurer.getTokenServices(), endpointsConfigurer.getClientDetailsService(), endpointsConfigurer.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }
}
