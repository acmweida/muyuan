package com.muyuan.gateway.base.config.swagger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;

/**
 * @ClassName SwaggerHeaderFilter
 * Description SwaggerHeaderFilter
 * @Author 2456910384
 * @Date 2022/2/10 11:15
 * @Version 1.0
 */
public class SwaggerHeaderFilter {

    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    private static final String URI = "/v3/api-docs";

    public static WebFilter getWebFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            if (!StringUtils.endsWithIgnoreCase(path, URI)) {
                return chain.filter(exchange);
            }

            String basePath = path.substring(0, path.lastIndexOf(URI));
            ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };
    }
}
