package com.yiming.learn.java.basic.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 * 随机数生成
 *
 * @author yiming
 * @since 2018-07-30 15:45.
 */
public class RandomNum {


    public static void main(String[] args) {

        int start = 1;
        int to = 50000;
        int count = 49999;

        long toEpochMilli = Instant.now().toEpochMilli();
        HashSet<Integer> set = gen1(start, to, count);
        List<Integer> list = Lists.newArrayList(set);
        System.out.println(list.size());
        System.out.println("gen1: " + (Instant.now().toEpochMilli() - toEpochMilli));

        toEpochMilli = Instant.now().toEpochMilli();
        list = gen2(start, to, count);
        System.out.println(list.size());
        System.out.println("gen2: " + (Instant.now().toEpochMilli() - toEpochMilli));

    }

    // 生成n个指定范围内的随机数
    private static HashSet<Integer> gen1(int min, int max, int count) {
        HashSet<Integer> numbers = Sets.newHashSet();
        if (count > max - min) {
            throw new IllegalArgumentException("生成的随机数数量不足" + count + "个, 请合调整理数量[1~" + (max - min) + "].");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("生成的随机数数量小于0, 请合调整理数量[1~" + (max - min) + "].");
        }
        int total = 0;
        while (count > numbers.size()) {
            total++;
            int e = new Random().nextInt(max - min + 1) + min; // 40592
//            int e = new Random().nextInt(max);// 44992
            if (e >= min && max >= e) {
                numbers.add(e);
            }
        }
        return numbers;
    }

    private static List<Integer> gen2(int min, int max, int count) {
        List<Integer> list = new ArrayList<>();
        for (int i = min; i < max; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        list = list.subList(0, count);
        return list;
    }

    @Test
    public void testGen2() {
        int times = 100;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            list = gen2(10, 5000, 4990);
        }
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        list.set(1, list.set(2, list.get(1)));

        System.out.println(list.get(1));
        System.out.println(list.get(2));

    }

}
