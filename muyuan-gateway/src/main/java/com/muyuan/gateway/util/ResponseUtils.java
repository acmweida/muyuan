package com.muyuan.gateway.util;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.enums.ResponseCode.*;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.JSONUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author hxr
 * @Date 2021-01-29 13:30
 */
public class ResponseUtils {

    public static Mono<Void> writeErrorInfo(ServerHttpResponse response, ResponseCode resultCode) {
        switch (resultCode) {
            case AUTHORIZED_ERROR:
            case TOKEN_INVALID_FAIL:
                break;
            default:
                break;
        }
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSONUtil.toJsonString(ResultUtil.fail(resultCode));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }

}
