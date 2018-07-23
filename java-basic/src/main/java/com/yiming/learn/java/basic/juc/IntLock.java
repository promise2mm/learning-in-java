package com.yiming.learn.java.basic.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yiming on 2018/7/24.
 */
public class IntLock implements Runnable {

    private ReentrantLock lock1 = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();

    private int lock;

    private static int num;

    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try{
          if (lock == 1) {
              lock1.lockInterruptibly();
              Thread.sleep(500);
              num++;
              lock2.lockInterruptibly();
          } else {
              lock2.lockInterruptibly();
              Thread.sleep(500);
              num++;
              lock1.lockInterruptibly();
          }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println("线程[" + Thread.currentThread().getId() + "]退出.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000 * 3);
        t2.interrupt();
        System.out.println(num);
    }
}
