package com.yiming.learn.algorithm.leetcode.easy;

import java.util.Objects;

/**
 * @author 一鸣
 * @date 2019/12/17 20:27
 */
public class LeetCode1290 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(1);
        LeetCode1290 obj = new LeetCode1290();
        int decimalValue = obj.getDecimalValue(head);
        System.out.println(decimalValue);
        System.out.println(obj.getDecimalValue(null));
        System.out.println(obj.getDecimalValue(new ListNode(0)));
        System.out.println(obj.getDecimalValue(new ListNode(1)));
    }

    public int getDecimalValue(ListNode head) {
        if (Objects.isNull(head)) {
            return 0;
        }
        // 1011
        StringBuilder binaryStr = new StringBuilder();
        while (Objects.nonNull(head)) {
            binaryStr.append(head.val);
            head = head.next;
        }
        char[] chars = binaryStr.toString().toCharArray();
        int resVal = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '1') {
                resVal += Math.pow(2, chars.length - i - 1);
            }
        }
        return resVal;
    }

    public int getDecimalValue2(ListNode head) {
        int res = 0;
        while (head != null) {
            res <<= 1;
            res += head.val;
            head = head.next;
        }
        return res;
    }

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}