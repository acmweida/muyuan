package com.muyuan.auth.base.config;

import com.muyuan.auth.base.exception.WebResponseExceptionHandler;
import com.muyuan.auth.base.login.ImageCaptchaAuthenticationFilter;
import com.muyuan.auth.base.login.ImageCaptchaAuthenticationProvider;
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
import jakarta.servlet.ServletOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfiguration {

    @Resource
    private DataSource dataSource;

    @Resource
    private UserServiceImpl userDetailsService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public RegisteredClientRepository clientRepository() {
        return new JdbcRegisteredClientRepository(new JdbcTemplate(dataSource));
    }


    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                        .oidc(Customizer.withDefaults());

        http.apply(authorizationServerConfigurer);

        WebResponseExceptionHandler webResponseExceptionHandler = new WebResponseExceptionHandler();

        http
                // Redirect to the login page when not authenticated from the
                // authorization endpoint
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
                // Accept access tokens for User Info and/or Client Registration
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt(Customizer.withDefaults()))
                .exceptionHandling(c -> c.accessDeniedHandler(webResponseExceptionHandler)
                        .authenticationEntryPoint(webResponseExceptionHandler)
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        ImageCaptchaAuthenticationProvider imageCaptchaAuthenticationProvider =
                new ImageCaptchaAuthenticationProvider(userDetailsService);

        return new ProviderManager(imageCaptchaAuthenticationProvider);
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler)
            throws Exception {

        WebResponseExceptionHandler webResponseExceptionHandler = new WebResponseExceptionHandler();

        ImageCaptchaAuthenticationFilter imageCaptchaAuthenticationFilter = new ImageCaptchaAuthenticationFilter(redisTemplate);
        imageCaptchaAuthenticationFilter.setAuthenticationManager(authenticationManager());
        imageCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        imageCaptchaAuthenticationFilter.setAuthenticationFailureHandler(webResponseExceptionHandler);

//        "/rsa/publicKey",
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/oauth/**", "/captchaImage", "/cancel", "/v3/**")
                                .permitAll()
                )
                .addFilterBefore(imageCaptchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .exceptionHandling(c ->
                        c.authenticationEntryPoint(webResponseExceptionHandler)
                                .accessDeniedHandler(webResponseExceptionHandler)
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(JwtEncoder jwtEncoder) {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();

            Instant now = Instant.now();
            long expiry = 36000L;
            // @formatter:off
            String scope = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(authentication.getName())
                    .claim("scope", scope)
                    .build();
            String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            outputStream.write(Objects.requireNonNull(JSONUtil.toJsonString(ResultUtil.success(token))).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        };
    }

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

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

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
