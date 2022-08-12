package com.muyuan.common.core.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName ApplicatoinContextHandler
 * Description ApplicationContextHandler
 * @Author 2456910384
 * @Date 2021/10/13 16:36
 * @Version 1.0
 */
public class ApplicationContextHandler implements ApplicationContextAware {

    private static ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
