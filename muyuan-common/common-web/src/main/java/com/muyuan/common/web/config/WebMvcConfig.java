package com.muyuan.common.web.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.web.aspect.LogAspect;
import com.muyuan.common.web.aspect.PreAuthorizeAspect;
import com.muyuan.common.web.aspect.RepeatableRequestAspect;
import com.muyuan.common.web.exception.ExceptionHandlerAdvice;
import com.muyuan.common.web.interceptor.HeaderInterceptor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import({PreAuthorizeAspect.class, RepeatableRequestAspect.class, ExceptionHandlerAdvice.class, LogAspect.class})
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public HttpMessageConverters jacksonConverters() {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(JSONUtil.objectMapper);
        List<MediaType> mediaTypes = new ArrayList<>(16);
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        mediaTypes.add(MediaType.APPLICATION_CBOR);
        mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);

        // 解决前端JS损失 Long精度问题
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        JSONUtil.objectMapper.registerModule(simpleModule);

        jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        return new HttpMessageConverters(jackson2HttpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHeaderInterceptor());
    }


    /**
     * 自定义请求头拦截器
     */
    public HeaderInterceptor getHeaderInterceptor()
    {
        return new HeaderInterceptor();
    }

}
