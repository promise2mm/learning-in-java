package com.yiming.learn.java.basic.exception;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class MyThreadPoolExecutor extends ThreadPoolExecutor {


    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    /**
     * 覆盖默认submit方法，将异常捕获并答应
     *
     * @param task
     * @return
     */
    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task));
    }

    private Runnable wrap(final Runnable task) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                log.warn(e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
