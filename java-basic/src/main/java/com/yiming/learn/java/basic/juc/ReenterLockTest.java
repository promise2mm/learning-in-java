package com.yiming.learn.java.basic.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yiming on 2018/7/23.
 */
public class ReenterLockTest implements Runnable {

    private ReentrantLock reentrantLock = new ReentrantLock();

    private static int num = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            reentrantLock.lock();
            try {
                num++;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockTest t = new ReenterLockTest();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(num);

    }
}
