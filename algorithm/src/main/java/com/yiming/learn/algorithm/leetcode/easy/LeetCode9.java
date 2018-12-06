package com.yiming.learn.algorithm.leetcode.easy;

/**
 * Created by 一鸣 on 2018/12/6 16:41.
 * Description:
 * <p>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * @author yiming
 */
public class LeetCode9 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(-121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int origin = x;
        int rev = 0;
        int pop = 0;
        while (x != 0) {
            pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev > Integer.MAX_VALUE / 10 + 7)) return false;
            if (rev < Integer.MIN_VALUE / 10 || (rev < Integer.MIN_VALUE / 10 - 8)) return false;
            rev = rev * 10 + pop;
        }
        return rev == origin;
    }


    public static boolean isPalindrome2(int x) {
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int revertedNum = 0;
        // 当x > revertedNum 时, 代表去除最后一位操作已达到一半
        // 1221
        while (x > revertedNum) {
            revertedNum = revertedNum * 10 + x % 10;
            x /= 10;
        }
        // 若x位数为偶数[1221], 此时的revertedNum == 12, x == 12 - 满足前面的判断
        // 若x位数为奇数[121], 此时的revertedNum = 12, x == 1 - 满足后面的判断
        return revertedNum == x || revertedNum / 10 == x;
    }


}
