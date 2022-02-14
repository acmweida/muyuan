package com.muyuan.common.core.domains.factories;

import com.muyuan.common.core.domains.service.FileService;
import com.muyuan.common.core.domains.service.impl.FastDFSFileService;
import com.muyuan.common.core.infrastructure.util.FastDFSClient;
import com.muyuan.common.core.domains.repo.FileRepo;
import com.muyuan.common.core.context.ApplicationContextHandler;
import org.springframework.stereotype.Component;

@Component
public class FileServiceFactory {

    public static FileService createFastDFSFileService() {
        return new FastDFSFileService(null, ApplicationContextHandler.getContext().getBean(FileRepo.class),ApplicationContextHandler.getContext().getBean(FastDFSClient.class));
    }


}
