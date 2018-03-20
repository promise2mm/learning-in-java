package com.yiming.concurrent;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

        FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> max1 = executorService.submit(task1);
        Future<Integer> max2 = executorService.submit(task2);
        executorService.shutdown();
        return Math.max(max1.get(), max2.get());
    }

    public static void main(String[] args) {
        int size = 100000;
        List<Integer> list = Stream.generate(() -> (int) (Math.random() * size)).limit(size).collect(Collectors.toList());
        try {
            System.out.println(max(list.toArray(new Integer[list.size()])));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
