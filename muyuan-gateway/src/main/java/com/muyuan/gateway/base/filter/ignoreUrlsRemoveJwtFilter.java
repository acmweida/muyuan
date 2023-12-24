package com.muyuan.gateway.base.filter;

import com.muyuan.gateway.base.config.IgnoreUrlsConfig;
import jakarta.annotation.Resource;
import org.apache.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * @ClassName WhiteListAuthorizationFilter
 * Description 白名单去除token
 * @Author 2456910384
 * @Date 2022/10/8 15:10
 * @Version 1.0
 */
@Component
public class ignoreUrlsRemoveJwtFilter implements WebFilter {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();

        List<String > urls = ignoreUrlsConfig.getIgnore();

        for (String  url : urls) {
            if (pathMatcher.match(url,path)) {
                request = exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION,"").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }

        return chain.filter(exchange);
    }
}
