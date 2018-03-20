package com.yiming.concurrent;

import java.util.concurrent.Callable;

/**
 * Created by yiming on 2018-03-20 20:04.
 * Description:
 * Demo from https://www.cnblogs.com/studyLog-share/p/5287559.html
 * just for learning
 */
public class FindMaxTask implements Callable<Integer> {

    private Integer[] data;

    private Integer start;

    private Integer end;

    @Override
    public Integer call() {
        if (data.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = data[0];
        for (int i = start; i < end; i++) {
            max = max < data[i] ? data[i] : max;
        }
        return max;
    }

    public FindMaxTask(Integer[] data, Integer start, Integer end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }
}
