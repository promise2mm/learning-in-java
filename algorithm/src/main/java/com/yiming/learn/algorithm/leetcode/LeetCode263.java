package com.yiming.learn.algorithm.leetcode;

import org.junit.Test;

/**
 * Created by yiming on 2018-07-19 09:58.
 *
 * Description:
 *
 * 编写一个程序判断给定的数是否为丑数。 丑数就是只包含质因数 2, 3, 5 的正整数。
 *
 * 说明： 1 是丑数。 输入不会超过 32 位有符号整数的范围: [−2^31,  2^31 − 1]。
 *
 * @author yiming
 */
public class LeetCode263 {

    @Test
    public void testIsUgly() {
        isUglyNumber(5);
    }

    private boolean isUglyNumber(int number) {
        int[] primers = new int[]{2, 3, 5};
        if (number <= 0) {
            return false;
        }
        if (number == 1) {
            return true;
        }

        double x = Math.log(2) / Math.log(number);
        double y = Math.log(3) / Math.log(number);
        double z = Math.log(5) / Math.log(number);
        System.out.println(String.format("x=%f, y=%f, z=%f", x, y, z));
        return false;
    }

}
