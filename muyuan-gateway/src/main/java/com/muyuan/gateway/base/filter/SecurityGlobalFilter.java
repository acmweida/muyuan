package com.muyuan.gateway.base.filter;

import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.gateway.util.ResponseUtils;
import com.nimbusds.jose.JWSObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 安全拦截全局过滤器
 *
 * @author <a href="mailto:xianrui0365@163.com">haoxr</a>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityGlobalFilter implements GlobalFilter, Ordered {

    private final RedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        log.info("remote:{} request url :{}",request.getRemoteAddress(),request.getPath());

        // 不是正确的的JWT不做解析处理
        String token = request.getHeaders().getFirst(SecurityConst.AUTHORIZATION_KEY);
        if (StringUtils.isEmpty(token) || !StringUtils.startsWithIgnoreCase(token, SecurityConst.JWT_PREFIX)) {
            return chain.filter(exchange);
        }

        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在则拦截访问
        token = StringUtils.replace(token, SecurityConst.JWT_PREFIX, StringUtils.EMPTY);
        String payload = JWSObject.parse(token).getPayload().toString();
        Map jsonObject =  JSONUtil.parseObject(payload, HashMap.class);
        String jti = (String) jsonObject.get(SecurityConst.JWT_JTI);
        Boolean isBlack = redisTemplate.hasKey(SecurityConst.TOKEN_BLACKLIST_PREFIX + jti);
        if (isBlack) {
            return ResponseUtils.writeErrorInfo(response, ResponseCode.TOKEN_INVALID_FAIL);
        }

        // 存在token且不是黑名单，request写入JWT的载体信息
        request = exchange.getRequest().mutate()
                .header(SecurityConst.JWT_PAYLOAD_KEY, URLEncoder.encode(payload, "UTF-8"))
                .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
