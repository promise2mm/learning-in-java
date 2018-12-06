package com.yiming.learn.algorithm.leetcode.easy;

/**
 * Created by 一鸣 on 2018/12/6 14:36.
 * Description: 整数反转
 *
 * @author yiming
 */
public class LeeCode7 {

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }

    public static int reverse(int x) {
        int res = 0;
        int count = 0; // 位数
        int temp = x;
        while (temp != 0) {
            count++;
            temp /= 10;
        }

        int[] digits = new int[count];
        int idx = 0;
        while (x != 0) {
            digits[idx++] = x % 10; // 最后一位
            x /= 10;
        }
        int radium = 1;
        for (int i = count; i > 0; i--, radium++) {
            res += Math.pow(10, count - radium) * digits[count - i];
        }
        if (res >= 0x7fffffff || res <= 0x80000000) {
            return 0;
        }
        return res;
    }

    /**
     * 最优解
     * @param x
     * @return
     */
    public static int reverse2(int x) {
        int rev = 0;
        int pop = 0;
        while (x != 0) {
            pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev > Integer.MAX_VALUE / 10 + 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev < Integer.MIN_VALUE / 10 - 8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

}
