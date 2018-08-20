package com.yiming.learn.java.basic.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * <p> ${DESCRIPTION} </p>
 *
 * @author 一鸣
 * @since 2018-08-20 19:26.
 */
public class ArrayListConcurrentBug {

    private AtomicInteger num = new AtomicInteger(0);

    @Test
    public void test() throws Exception {
        for (int j = 0; j < 10; j++) {
            testThread();
        }
    }

    private void testThread() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        // 线程安全
//        List<Integer> ids = Collections.synchronizedList(new ArrayList<>());
//        List<Integer> ids = new Vector<>();
        // add方法线程不安全
        List<Integer> ids = new ArrayList<>();
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<?> future = service.submit(() -> {
                ids.add(mockGetDataFromDB());
            });
            futures.add(future);
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(ids);
        System.out.println(ids.size() + "," + ids.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList()).size());
    }

    private Integer mockGetDataFromDB() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num.getAndIncrement();
    }

}
