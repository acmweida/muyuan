package com.muyuan.auth.base.config;

import com.muyuan.auth.base.exception.WebResponseExceptionTranslator;
import com.muyuan.auth.base.oauth2.ImageCaptchaAuthenticationProvider;
import com.muyuan.auth.service.impl.UserServiceImpl;
import com.muyuan.auth.util.JwtUtils;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.ResultUtil;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfiguration {

    @Resource
    private DataSource dataSource;

    @Resource
    private UserServiceImpl userDetailsService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


//    @Bean
//    @Order(1)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
//                http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
////                        .tokenEndpoint(tokenEndpoint -> {
////                            tokenEndpoint.accessTokenRequestConverter(
////                                            new ImageCaptchaAuthenticationConverter()
////                                    )
////                                    .authenticationProvider(
////                                            new ImageCaptchaAuthenticationProvider(userDetailsService, redisTemplate)
////                                    )
////                            ;
////                        })
//                        .oidc(Customizer.withDefaults());
//
//        http.apply(authorizationServerConfigurer);
//
////        WebResponseExceptionTranslator webResponseExceptionTranslator = new WebResponseExceptionTranslator();
//
////        val imageCaptchaAuthenticationProvider = new ImageCaptchaAuthenticationProvider(userDetailsService, redisTemplate);
//
//        http
//                // Redirect to the login page when not authenticated from the
//                // authorization endpoint
//                .exceptionHandling((exceptions) -> exceptions
//                        .defaultAuthenticationEntryPointFor(
//                                new LoginUrlAuthenticationEntryPoint("/login"),
//                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
//                        )
//                )
//                // Accept access tokens for User Info and/or Client Registration
//                .oauth2ResourceServer((resourceServer) -> resourceServer
//                        .jwt(Customizer.withDefaults()))
////                .exceptionHandling(c -> c.accessDeniedHandler(webResponseExceptionTranslator)
////                        .authenticationEntryPoint(webResponseExceptionTranslator)
////                )
//
//
//        ;
//
//        return http.build();
//    }


    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,JwtUtils jwtUtils)
            throws Exception {

        ImageCaptchaAuthenticationProvider imageCaptchaAuthenticationProvider =
                new ImageCaptchaAuthenticationProvider(userDetailsService,redisTemplate);
        WebResponseExceptionTranslator webResponseExceptionTranslator = new WebResponseExceptionTranslator();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/oauth/**", "/rsa/publicKey", "/captchaImage", "/cancel", "/v3/**","/login")
                                .permitAll()
                )
                .formLogin(c -> c.successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=UTF-8");
                        ServletOutputStream outputStream = response.getOutputStream();

                        // 生成JWT，并放置到请求头中
//                        String jwt = generator.generate(authentication);

                        String token = jwtUtils.generateToken(authentication, System.currentTimeMillis() + 6 * 60 * 60);

                        outputStream.write(JSONUtil.toJsonString(ResultUtil.success(token)).getBytes(StandardCharsets.UTF_8));
                        outputStream.flush();
                        outputStream.close();
                    }
                }))
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .exceptionHandling(c ->
                        c.accessDeniedHandler(webResponseExceptionTranslator)
                        .authenticationEntryPoint(webResponseExceptionTranslator)
                );
        // Form login handles the redirect to the login page from the
        // authorization server filter chain
//                .formLogin(Customizer.withDefaults());

        AuthenticationManagerBuilder manager = http.getSharedObject(AuthenticationManagerBuilder.class);
        manager.authenticationProvider(imageCaptchaAuthenticationProvider);

        return http.build();
    }

//    @Bean
//    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        return new InMemoryUserDetailsManager(User.withUsername("admin001")
//                .password("{noop}admin001").roles("USER").build());
//    }


//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        PasswordEncoder passwordEncoder = passwordEncoder();
//            RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                    .clientId("WEB-CLIENT")
//                    .clientSecret(passwordEncoder.encode("123456"))
//                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                    .redirectUri("http://127.0.0.1:8080/authorized")
//                    .scope("scope-a")
//                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                    .build();
//        JdbcRegisteredClientRepository jdbcRegisteredClientRepository = new JdbcRegisteredClientRepository(new JdbcTemplate(dataSource));
//            jdbcRegisteredClientRepository.save(registeredClient);
//        return jdbcRegisteredClientRepository;
//    }


    @Bean
    public JwtUtils jwtUtils(KeyPair keyPair) {
        return new JwtUtils(keyPair);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = keyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

//    @Bean
//    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
//        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
//    }
//
//    @Bean
//    public AuthorizationServerSettings authorizationServerSettings() {
//        return AuthorizationServerSettings.builder().build();
//    }

    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }



//    @Bean
//    public OAuth2TokenGenerator<?> tokenGenerator(JwtEncoder jwtEncoder) {
//        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
//        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
//        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
//        return new DelegatingOAuth2TokenGenerator(
//                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
//    }

}
