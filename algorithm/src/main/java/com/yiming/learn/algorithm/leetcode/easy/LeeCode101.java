package com.yiming.learn.algorithm.leetcode.easy;

import java.util.Objects;
import lombok.Data;

/**
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 说明:
 *
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 */
public class LeeCode101 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compareSameLevelNode(root.left, root.right);
    }

    private boolean compareSameLevelNode(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val
            && compareSameLevelNode(left.left, right.right)
            && compareSameLevelNode(left.right, right.left);
    }

    public class TreeNode {
          int val;
          TreeNode right;
          TreeNode left;
          TreeNode(int x) { val = x; }
    }

}
