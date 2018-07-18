package com.yiming.learn.java.basic.concurrent;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by yiming on 2018-07-18 14:05. Description:
 *
 * @author yiming
 */
public class CallableFutureTest {

    private static final Integer MILLISECOND = 1000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = Instant.now().toEpochMilli();
        ExecutorService executorService = Executors.newCachedThreadPool();
        // Future 方式
        Future<Integer> future1 = executorService.submit(() -> runTask(Thread.currentThread(), 5));
        Future<Integer> future2 = executorService.submit(() -> runTask(Thread.currentThread(), 10));

        // FutureTask方式
        FutureTask<Integer> futureTask = new FutureTask<>(() -> runTask(Thread.currentThread(), 8));
        executorService.submit(futureTask);

        // submit之后就可以关闭了, executorService会执行已经submit的task, 拒绝接受新的task
        executorService.shutdown();
        // shutdown后再submit会跑出异常: java.util.concurrent.RejectedExecutionException
        // executorService.submit(() -> runTask(Thread.currentThread(), 2));

        System.out.println(Thread.currentThread().getName() + " running...");
        Thread.sleep(2 * MILLISECOND);
        // 从FutureTask中阻塞等待返回值
        System.out.println("子线程3执行结果: " + futureTask.get());
        System.out.println("子线程2执行结果: " + future2.get());
        System.out.println("子线程1执行结果: " + future1.get());
        // 最终花费时间约等于10s, 即说明三个线程是并发执行的, 若是线性执行的时间必然是: 5s + 10s + 8s = 23s
        System.out.println("elapsed: " + (Instant.now().toEpochMilli() - start) / 1000d + "s.");
        System.out.println("end.");
    }

    private static Integer runTask(Thread thread, Integer sleepSeconds) throws InterruptedException {
        System.out.println(thread.getName() + " running...");
        Thread.sleep(sleepSeconds * MILLISECOND);
        return new Random().nextInt(1000);
    }
}
