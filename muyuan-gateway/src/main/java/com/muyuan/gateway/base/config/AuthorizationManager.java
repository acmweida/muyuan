package com.muyuan.gateway.base.config;

import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;
import com.muyuan.common.constant.auth.SecurityConstants;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisTemplate<String, Object> redisTemplate;

    public AuthorizationManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 1、从Redis中获取当前路径可访问角色列表
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = authorizationContext.getExchange().getRequest().getURI();

        // 1. 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 2. token为空拒绝访问
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (StrUtil.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }


        Object obj = redisTemplate.opsForHash().get(AuthRedisConst.RESOURCE_ROLES_MAP, uri.getPath());
        List<String> authorities = Collections.EMPTY_LIST;
//        if (null != obj) {
            String[] split = "SHOP_KEEPER".split(",");
            authorities = Arrays.asList(split);
//        }
        authorities = authorities.stream().map(i -> i = SecurityConstants.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        // 2、认证通过且角色匹配的用户可访问当前路径
        List<String> finalAuthorities = authorities;
        // 判断JWT中携带的用户角色是否有权限访问
        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority -> {
                    String roleCode = authority.substring(SecurityConstants.AUTHORITY_PREFIX.length()); // 用户的角色
                    if (SecurityConstants.ROOT_ROLE_CODE.equals(roleCode)) {
                        return true; // 如果是超级管理员则放行
                    }
                    boolean hasAuthorized = !CollectionUtils.isEmpty(finalAuthorities) && finalAuthorities.contains(roleCode);
                    return hasAuthorized;
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecisionMono;
    }
}
