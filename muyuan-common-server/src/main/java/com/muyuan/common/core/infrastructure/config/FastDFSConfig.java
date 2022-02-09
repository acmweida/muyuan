package com.muyuan.common.core.infrastructure.config;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class FastDFSConfig {

    @Bean
    public StorageClient1 trackerServer(@Value("${fastDFS.profiles.active}") String fastDFSConfigPath) throws IOException, MyException {
        Properties properties = new Properties();
        Resource resource = new ClassPathResource(fastDFSConfigPath);

        TrackerServer trackerServer;
        StorageClient1 storageClient = null;
        StorageServer storageServer = null;
        try {
            properties.load(resource.getInputStream());
            ClientGlobal.initByProperties(properties);
            trackerServer = new TrackerClient().getTrackerServer();
            storageClient = new StorageClient1(trackerServer,storageServer);
        } catch (IOException  | MyException e) {
            e.printStackTrace();
        }

        return  storageClient;
    }

}
