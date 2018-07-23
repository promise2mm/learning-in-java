package com.yiming.learn.java.basic.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yiming on 2018/7/24.
 */
public class TimeLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
//            if (lock.tryLock(5, TimeUnit.SECONDS)){
            if (lock.tryLock()){
                System.out.println(Thread.currentThread().getName() + " got lock.");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName() + " get lock fail.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
