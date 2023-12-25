package com.muyuan.system.base.config;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class FastDFSConfig implements InitializingBean {

    @Value("${fastDFS.profiles.active}")
    private String fastDFSConfigPath;

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = new Properties();
        Resource resource = new ClassPathResource(fastDFSConfigPath);

        try {
            properties.load(resource.getInputStream());
            ClientGlobal.initByProperties(properties);
        } catch (IOException  | MyException e) {
            e.printStackTrace();
        }

    }

}
