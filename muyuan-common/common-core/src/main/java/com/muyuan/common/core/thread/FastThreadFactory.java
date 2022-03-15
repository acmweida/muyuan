package com.muyuan.common.core.thread;

import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName FastThreadFactory
 * Description fasf线程工程
 * @Author 2456910384
 * @Date 2021/10/12 11:22
 * @Version 1.0
 */
public class FastThreadFactory implements ThreadFactory {

    private static FastThreadFactory instance = null;

    private static Object lock = new Object();

    private  FastThreadFactory() {

    }

    public static FastThreadFactory getInstance() {
        if (null == instance) {
            synchronized (lock) {
                if (null == instance) {
                    instance = new FastThreadFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new FastThreadLocalThread();
    }
}
