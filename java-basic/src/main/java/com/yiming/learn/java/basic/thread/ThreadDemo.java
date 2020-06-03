package com.yiming.learn.java.basic.thread;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import sun.misc.Unsafe;

/**
 * 对一个值进行多线程累加操作，分别比较不同的方式的性能
 * <pre>
 * 1 - synchronized(Integer)
 * 2 - synchronized(int)
 * 3 - AtomicInteger - 无锁
 * 4 - Unsafe - 无锁
 * </pre>
 */
public class ThreadDemo {

    static final int times = 1000000;
    static final int threadNum = 20;
    private static Unsafe unsafe;
    private static long valueOffset;

    public static void main(String[] args) throws Exception {
        List<Count> counts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            counts.add(doTest());
            System.out.println(i + "===============================");
        }
        Statistics statisticsA = new Statistics("synchronized-Integer");
        statisticsA.setMax(counts.stream().mapToLong(Count::getA).max().getAsLong());
        statisticsA.setMin(counts.stream().mapToLong(Count::getA).min().getAsLong());
        statisticsA.setAvg(counts.stream().mapToLong(Count::getA).average().getAsDouble());

        Statistics statisticsB = new Statistics("synchronized-int");
        statisticsB.setMax(counts.stream().mapToLong(Count::getB).max().getAsLong());
        statisticsB.setMin(counts.stream().mapToLong(Count::getB).min().getAsLong());
        statisticsB.setAvg(counts.stream().mapToLong(Count::getB).average().getAsDouble());

        Statistics statisticsC = new Statistics("AtomicInteger");
        statisticsC.setMax(counts.stream().mapToLong(Count::getC).max().getAsLong());
        statisticsC.setMin(counts.stream().mapToLong(Count::getC).min().getAsLong());
        statisticsC.setAvg(counts.stream().mapToLong(Count::getC).average().getAsDouble());

        Statistics statisticsD = new Statistics("Unsafe");
        statisticsD.setMax(counts.stream().mapToLong(Count::getD).max().getAsLong());
        statisticsD.setMin(counts.stream().mapToLong(Count::getD).min().getAsLong());
        statisticsD.setAvg(counts.stream().mapToLong(Count::getD).average().getAsDouble());

        System.out.println(statisticsA);
        System.out.println(statisticsB);
        System.out.println(statisticsC);
        System.out.println(statisticsD);
    }


    @Data
    static class Statistics {

        private String name;
        private long min;
        private long max;
        private double avg;

        public Statistics(String name) {
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    static class Count {

        private long a;
        private long b;
        private long c;
        private long d;
    }

    private static Count doTest() throws InterruptedException, IllegalAccessException, NoSuchFieldException {
        A a = new A();
        long timer1 = synchronizedTestInteger(a);
        System.out.println("1. synchronized(Integer):\t" + timer1 + "ms. \t" + a.value);

        B b = new B();
        long timer2 = synchronizedTestInt(b);
        System.out.println("2. synchronized(int):\t" + timer2 + "ms. \t" + b.value);

        final C c = new C();
        long timer3 = atomicTest(c);
        System.out.println("3. atomic:\t" + timer3 + "ms. \t" + c.value);

        initUnsafe();
        final B d = new B();
        long timer4 = unsafeTest(d);
        System.out.println("4. unsafe(int):\t" + timer4 + "ms. \t" + d.value);
        return new Count(timer1, timer2, timer3, timer4);
    }

    private static void initUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
        singleoneInstanceField.setAccessible(true);
        unsafe = (Unsafe) singleoneInstanceField.get(null);
        try {
            valueOffset = unsafe.objectFieldOffset
                (B.class.getDeclaredField("value"));
        } catch (Exception e) {

        }
    }

    private static long atomicTest(C c) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<WorkerB> workers = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            WorkerB worker = new WorkerB(c);
            worker.start();
            workers.add(worker);
        }
        for (WorkerB worker : workers) {
            worker.join();
        }
        return System.currentTimeMillis() - start;
    }

    private static long synchronizedTestInteger(A a) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<WorkerA> workers = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            WorkerA worker = new WorkerA(a);
            worker.start();
            workers.add(worker);
        }
        for (WorkerA worker : workers) {
            worker.join();
        }
        return System.currentTimeMillis() - start;
    }

    private static long synchronizedTestInt(B b) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<WorkerC> workers = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            WorkerC worker = new WorkerC(b);
            worker.start();
            workers.add(worker);
        }
        for (WorkerC worker : workers) {
            worker.join();
        }
        return System.currentTimeMillis() - start;
    }

    private static long unsafeTest(B b) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<WorkerD> workers = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            WorkerD worker = new WorkerD(b);
            worker.start();
            workers.add(worker);
        }
        for (WorkerD worker : workers) {
            worker.join();
        }
        return System.currentTimeMillis() - start;
    }

    static class A {

        private Integer value = 0;
    }

    static class C {

        private AtomicInteger value = new AtomicInteger(0);
    }

    static class B {

        private int value = 0;
    }

    static class WorkerA extends Thread {

        private A a;

        public WorkerA(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            for (int j = 0; j < times; j++) {
                synchronized (a) {
                    a.value++;
                }
            }
        }
    }

    static class WorkerB extends Thread {

        private C c;

        public WorkerB(C c) {
            this.c = c;
        }

        @Override
        public void run() {
            for (int j = 0; j < times; j++) {
                c.value.addAndGet(1);
            }
        }
    }

    static class WorkerC extends Thread {

        private B b;

        public WorkerC(B b) {
            this.b = b;
        }

        @Override
        public void run() {
            for (int j = 0; j < times; j++) {
                synchronized (b) {
                    b.value++;
                }
            }
        }
    }

    static class WorkerD extends Thread {

        private B b;

        public WorkerD(B b) {
            this.b = b;
        }

        @Override
        public void run() {
            for (int j = 0; j < times; j++) {
                unsafe.getAndAddInt(b, valueOffset, 1);
            }
        }
    }

}
