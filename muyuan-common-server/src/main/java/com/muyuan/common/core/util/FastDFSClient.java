package com.muyuan.common.core.util;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.thread.CommonThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.UploadCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName FastDFSClient
 * Description FastDFS工具类
 * @Author 2456910384
 * @Date 2021/10/11 15:13
 * @Version 1.0
 */
@Component
@Slf4j
public class FastDFSClient {

    @Autowired
    StorageClient1 storageClient1;

    /**
     * 小文件上传  1MB 以内
     * @param fileExtendName
     * @param fileStream
     * @return
     * @throws MyException
     * @throws IOException
     */
    public String uploadFile(String fileExtendName,InputStream fileStream) throws MyException, IOException {
        return uploadFile(null,fileExtendName,fileStream);
    }

    /**
     * 小文件上传  1MB 以内
     * @param group
     * @param fileExtendName
     * @param fileStream
     * @return
     * @throws MyException
     * @throws IOException
     */
    public String uploadFile(String group,String fileExtendName,InputStream fileStream) throws MyException, IOException {
        Assert.isTrue(fileStream.available() <= GlobalConst.MB,"file size > 1MB");
        String filePath = storageClient1.upload_file1(group, fileStream.available(),new UploadFaction(fileStream), fileExtendName, new NameValuePair[]{});
        return filePath;
    }

    /**
     * 批量上传小文件
     * @param group
     * @param fileExtendNames
     * @param fileStreams
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public String[] uploadFiles(String group, String[] fileExtendNames, List<InputStream> fileStreams) throws InterruptedException, ExecutionException, TimeoutException {
        Assert.notEmpty(fileExtendNames,"fileExtendNames is empty");
        Assert.notEmpty(fileStreams,"fileStreams is empty");
        Assert.isTrue(fileExtendNames.length == fileStreams.size(),"fileExtendNames length not equal fileStreams length");
        CollectionAssert.notContainNull(fileStreams,"fileExtendNames has null element");
        CollectionAssert.notContainNull(fileStreams,"fileStreams has null element");

        String[] filePaths = new String[fileStreams.size()];
        List<UploadTask> tasks = new ArrayList<>();
        int index = 0;
        for (String fileExtendName : fileExtendNames) {
            tasks.add(new UploadTask(fileStreams.get(index),storageClient1,group,fileExtendName));
        }

        final List<Future<String>> futures = CommonThreadPool.invokeAll(tasks);
        index = 0;
        while (index != futures.size() -1 ) {
            Future<String> f = futures.get(index);
            String filePath = f.get(5, TimeUnit.SECONDS);
            filePaths[index] = filePath;
            index++;
        }

        return filePaths;
    }

    /**
     * 上传回调方法
     */
    static class UploadFaction implements UploadCallback {
        private int start = 0;
        private byte[] temp = new byte[1024];
        protected InputStream fileStream;

        public UploadFaction(InputStream fileStream) {
            Assert.notNull(fileStream,"fileStream is null");
            this.fileStream = fileStream;
        }

        @Override
        public int send(OutputStream outputStream) throws IOException {
            int readByteCount = fileStream.read(temp, start, (int) GlobalConst.KB);
            outputStream.write(temp, 0, readByteCount);
            return readByteCount;
        }

    }

    /**
     * 文件上传
     */
    public static class UploadTask extends UploadFaction implements  Callable<String> {
        private StorageClient1 storageClient1;
        private String group;
        private String fileExtendName;

        public UploadTask(InputStream fileStream,StorageClient1 storageClient1,String group,String fileExtendName) {
            super(fileStream);
            Assert.notNull(storageClient1,"storageClient1 is null");
            this.storageClient1 = storageClient1;
            this.group = group;
            this.fileExtendName = fileExtendName;
        }

        @Override
        public String call() throws Exception {
            return  storageClient1.upload_file1(group, super.fileStream.available(),new UploadFaction(super.fileStream), fileExtendName, new NameValuePair[]{});
        }
    }
}
