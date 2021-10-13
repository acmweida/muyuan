package com.muyuan.common.domains.factories;

import com.muyuan.common.domains.repo.FileRepo;
import com.muyuan.common.domains.service.FileService;
import com.muyuan.common.domains.service.impl.FastDFSFileService;
import com.muyuan.common.infrastructure.util.FastDFSClient;
import com.muyuan.common.spring.ApplicationContextHandler;
import org.springframework.stereotype.Component;

@Component
public class FileServiceFactory {

    public static FileService createFastDFSFileService() {
        return new FastDFSFileService(null, ApplicationContextHandler.getContext().getBean(FileRepo.class),ApplicationContextHandler.getContext().getBean(FastDFSClient.class));
    }


}
