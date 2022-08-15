package com.muyuan.auth.base.config;

import com.muyuan.auth.base.oauth2.ImageCaptchaAuthenticationProvider;
import com.muyuan.auth.service.impl.UserServiceImpl;
import com.muyuan.common.web.config.WebMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Import(WebMvcConfig.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(new ImageCaptchaAuthenticationProvider(userDetailsService));
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
              .antMatchers("/oauth/**","/rsa/publicKey","/login/**","/v2/**").permitAll()
              .anyRequest().authenticated()
              .and().csrf().disable();
    }
}
