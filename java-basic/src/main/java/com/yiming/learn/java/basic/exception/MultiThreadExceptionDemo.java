package com.yiming.learn.java.basic.exception;

import java.util.concurrent.*;

public class MultiThreadExceptionDemo {

    private static final Integer NUM_OF_PROCESSOR = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor EXECUTOR = new MyThreadPoolExecutor(
            NUM_OF_PROCESSOR, NUM_OF_PROCESSOR * 10,
            1L, TimeUnit.MINUTES, new LinkedBlockingDeque<>(),
            Executors.defaultThreadFactory()
    );

    public static void main(String[] args) {

        // 1. 直接使用execute方法会将异常信息抛出
        //exceptionThrownByExecute();

        // 2. 直接调用submit方法会将异常吞没
        exceptionSwallowedBySubmit();

        // 3. 使用 future.get() 可以抛出异常
        //exceptionThrownBySubmitWithFuture();

        EXECUTOR.shutdown();
    }


    private static void exceptionThrownBySubmitWithFuture() {
        Future<?> future = EXECUTOR.submit(new CalculateThread());
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void exceptionSwallowedBySubmit() {
        EXECUTOR.submit(new CalculateThread());
    }

    /**
     * 使用execute方法会直接抛出异常
     */
    private static void exceptionThrownByExecute() {
        EXECUTOR.execute(new CalculateThread());
    }

    static class CalculateThread implements Runnable {

        @Override
        public void run() {
            System.out.println(1 / 0);
        }
    }

}
