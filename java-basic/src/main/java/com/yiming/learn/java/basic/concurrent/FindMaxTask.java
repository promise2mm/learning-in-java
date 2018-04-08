package com.yiming.learn.java.basic.concurrent;

import java.util.concurrent.Callable;

/**
 * Created by yiming on 2018-03-20 20:04.
 * Description:
 * Demo from https://www.cnblogs.com/studyLog-share/p/5287559.html
 * just for learning
 */
public class FindMaxTask implements Callable<Integer> {

    private Integer[] data;

//    private Integer start;
//
//    private Integer end;

    @Override
    public Integer call() {
        String name = Thread.currentThread().getName();
        System.out.println("子线程 " + name +" 正在计算" + data.length + "个数中最大的数...");
        if (data.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = data[0];
        for (int i = 0; i < data.length; i++) {
            max = max < data[i] ? data[i] : max;
        }
        System.out.println("子线程 " + name +" 计算完毕, 得到结果" + max +".");
        return max;
    }

    public FindMaxTask(Integer[] data, Integer start, Integer end) {
        this.data = data;
        //this.start = start;
        //this.end = end;
    }

    public FindMaxTask(Integer[] data) {
        this.data = data;
    }
}
