package com.yiming.learn.java.basic.concurrent;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yiming on 2018-03-20 20:04.
 * Description:
 * Demo from https://www.cnblogs.com/studyLog-share/p/5287559.html
 * just for learning
 */
public class MultiThreadMaxNumberFinder {

    public static Integer max(Integer[] data) throws ExecutionException, InterruptedException {

        if (data.length == 1) {
            return data[0];
        }
        if (data.length == 0) {
            throw new IllegalArgumentException("array must not be empty!");
        }

        int core = Runtime.getRuntime().availableProcessors();
        BlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(core, core * 4, 1, TimeUnit.MINUTES, blockingDeque, threadFactory);
        int workCount = 10;
        int size = data.length / workCount;
        int max = 0;
        for (int i = 0; i < workCount; i++) {
            Integer[] temp = new Integer[size];
            System.arraycopy(data, i * size, temp, 0, size);
            FindMaxTask task = new FindMaxTask(temp);
            Future<Integer> future = poolExecutor.submit(task);
            Integer result = future.get();
            if (result.compareTo(max) > 0) {
                max = result;
            }
        }
        poolExecutor.shutdown();
        return max;
    }

    public static void main(String[] args) {
        int size = 1000000;
        System.out.println("正在生成" + size + "个随机数...");
        List<Integer> list = Stream.generate(() -> (int) (Math.random() * 100000L)).parallel().limit(size).collect(Collectors.toList());
        System.out.println("生成随机数完毕.");
        try {
            System.out.println(max(list.toArray(new Integer[list.size()])));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
