
package com.muyuan.common.web.config;

import feign.RequestInterceptor;
import feign.codec.Encoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * Feign相关配置类
 *
 * @author Gadfly
 * @since 2021-08-06 9:47
 */

@Configuration
public class FeignConfig {
    /**
     * 让DispatcherServlet向子线程传递RequestContext
     *
     * @param servlet servlet
     * @return 注册bean
     */
    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet servlet) {
        servlet.setThreadContextInheritable(true);
        return new ServletRegistrationBean<>(servlet, "/**");
    }

    @Bean
    public Encoder feignEncoder(HttpMessageConverters httpMessageConverters) {
        return new SpringEncoder(() -> httpMessageConverters);

    }

    /**
     * 覆写拦截器，在feign发送请求前取出原来的header并转发
     *
     * @return 拦截器
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return (template) -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            //获取请求头
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    //将请求头保存到模板中
                    template.header(name, values);
                }
            }
        };
    }
}

