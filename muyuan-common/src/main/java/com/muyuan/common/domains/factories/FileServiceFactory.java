package com.muyuan.common.domains.factories;

import com.muyuan.common.domains.repo.FileRepo;
import com.muyuan.common.domains.service.FileService;
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

    public static FileService createFileService() {
        return new FileService(null,context.getBean(FileRepo.class));
    }


}
