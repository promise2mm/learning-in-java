package com.yiming.learn.java.basic.lang;

import java.lang.reflect.Field;

/**
 * Created by yiming on 2018-07-20 12:21. Description:
 *
 * @author yiming
 */
public class IntegerTest {
    public static void main(String[] args) {
        try {
            test();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // 循环时调用Integer.valueOf(i)
        // @see Integer.valueOf(int i)
        for (Integer i = 1; i < 4; i++) {
            System.out.println(i);
        }
    }

    private static void test() throws NoSuchFieldException, IllegalAccessException {
        Integer x = 100;
        Class c = Integer.class.getDeclaredClasses()[0];
        Field f = c.getDeclaredField("cache");
        f.setAccessible(true);
        Integer[] cache = (Integer[]) f.get(x);
        cache[130] = cache[131];
    }

}


