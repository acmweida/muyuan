package com.muyuan.common.domains.factories;

import com.muyuan.common.domains.repo.FileRepo;
import com.muyuan.common.domains.service.FileService;
import com.muyuan.common.domains.service.impl.FastDFSFileService;
import com.muyuan.common.infrastructure.util.FastDFSClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class FileServiceFactory implements ApplicationContextAware {

    private static ApplicationContext  context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static FileService createFastDFSFileService() {
        return new FastDFSFileService(null,context.getBean(FileRepo.class),context.getBean(FastDFSClient.class));
    }


}
