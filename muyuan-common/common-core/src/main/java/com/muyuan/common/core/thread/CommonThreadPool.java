package com.muyuan.common.core.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName CommonThreadPool
 * Description 公共线程池 运行简单 快速任务
 * @Author 2456910384
 * @Date 2021/10/12 11:24
 * @Version 1.0
 */
@Slf4j
public class CommonThreadPool {

    private  static  int CORE_THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private static int MAX_THREAD_COUNT = CORE_THREAD_COUNT << 1;

    private static ScheduledThreadPoolExecutor threadPoolExecutor = null;

    private static int KEEP_ALIVE_TIME = 5;

    private static int MAX_TASK_COUNT = 1 << 5;

    private static BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(MAX_TASK_COUNT);

    static {

        threadPoolExecutor = new ScheduledThreadPoolExecutor(CORE_THREAD_COUNT,FastThreadFactory.getInstance(),new ThreadPoolExecutor.CallerRunsPolicy());
        log.info("common thread pool init success!");
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    /**
                     * TODO : 可以保存任务记录重启后执行  考虑多机情况
                     */
//                    List<Runnable> runnables = threadPoolExecutor.shutdownNow();

                    threadPoolExecutor.shutdown();
                }
        ));
    }

    public static boolean isFull() {
        return MAX_TASK_COUNT == queue.size();
    }

    public static  <T> List<Future<T>>  invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return threadPoolExecutor.invokeAll(tasks);
    }


    public static  <T> Future  schedule(Callable tasks,long second) {
        return threadPoolExecutor.schedule(tasks,second,TimeUnit.SECONDS);
    }

    public static  <T> Future  schedule(Runnable tasks,long second) {
        return threadPoolExecutor.schedule(tasks,second,TimeUnit.SECONDS);
    }


}
