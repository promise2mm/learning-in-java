package com.yiming.java8.stream;

import java.util.stream.Stream;

/**
 * Created by yiming on 2018/1/11.
 */
public class StreamTest {

    public static void main(String[] args) {
        //Lists是Guava中的一个工具类
//        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
//        nums.stream().filter(num -> num != null).collect(nums );
//        nums.stream().forEach(System.out::println);
        Stream.generate(Math::random).limit(100).forEach(System.out::println);
    }

}
