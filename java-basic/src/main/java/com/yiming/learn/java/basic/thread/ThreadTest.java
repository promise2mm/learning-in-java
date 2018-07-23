package com.yiming.learn.java.basic.thread;

import org.junit.Test;

/**
 * Created by yiming on 2018/7/23.
 */
public class ThreadTest {


    public static void main(String[] args) throws InterruptedException {
        // 当只有守护线程时, 虚拟机会自然退出
        // 若不将t设为守护线程, 则此程序会不断打印"alive"
        DemoThread t = new DemoThread();
        t.setDaemon(true);
        t.start();

        Thread.sleep(1000);
    }

    static class DemoThread extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.println("alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
