package com.yiming.learn.algorithm.other;

import com.alibaba.fastjson.JSON;

/**
 * Created by yiming on 2018/7/25.
 */
public class MiTest {


    public static void main(String[] args) {

        int[] arr = new int[13];
        for (int i = 0; i< 13; i++) {
            arr[i] = i + 1;
        }
        System.out.println(JSON.toJSONString(arr));
        int[] newArr = new int[arr.length];
        for (int i : arr) {

        }

    }

}
