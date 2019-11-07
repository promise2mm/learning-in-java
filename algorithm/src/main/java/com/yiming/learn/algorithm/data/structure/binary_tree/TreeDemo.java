package com.yiming.learn.algorithm.data.structure.binary_tree;

import com.alibaba.fastjson.JSON;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * 二叉树复制Demo
 */

@Slf4j
public class TreeDemo<T> {

    public static void main(String[] args) {
        // 构建一棵二叉树
        BinaryTree<Integer> srcTree = getBinaryTree();
        srcTree.setName("src");

        final BinaryTree copiedTree = copy(srcTree, "src");
        log.warn("{}", JSON.toJSONString(srcTree));
        log.warn("{}", JSON.toJSONString(copiedTree));
        log.warn("{}", Objects.equals(srcTree, copiedTree)); // true

    }

    /**
     * 二叉树复制
     *
     * @param sourceTree 源二叉树
     * @param name root节点名称(用以区分树的异同)
     * @param <T> 节点数据类型
     * @return 复制后的二叉树
     */
    private static <T> BinaryTree copy(BinaryTree<T> sourceTree, String name) {
        TreeNode<T> srcRoot = sourceTree.getRoot();
        BinaryTree<T> targetTree = new BinaryTree<>();
        targetTree.setName(name);

        // 根节点复制
        TreeNode<T> targetRoot = new TreeNode<>();
        targetTree.setRoot(targetRoot);
        targetRoot.setData(srcRoot.getData());
        targetRoot.setLeft(buildNode(srcRoot.getLeft(), DIRECTION.LEFT));
        targetRoot.setRight(buildNode(srcRoot.getRight(), DIRECTION.RIGHT));
        return targetTree;
    }

    /**
     * 递归复制二叉树
     *
     * @param node 当前节点
     * @param direction 左 or 右
     * @param <T> 节点数据类型
     * @return 复制完成的二叉树
     */
    private static <T> TreeNode<T> buildNode(TreeNode<T> node, DIRECTION direction) {
        if (!node.getIsLeaf()) {
            if (DIRECTION.LEFT.equals(direction)) {
                node.setLeft(buildNode(node.getLeft(), DIRECTION.LEFT));
            }
            if (DIRECTION.RIGHT.equals(direction)) {
                node.setRight(buildNode(node.getRight(), DIRECTION.RIGHT));
            }
        }
        return node;
    }

    enum DIRECTION {
        LEFT, RIGHT;
    }

    private static BinaryTree<Integer> getBinaryTree() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        TreeNode<Integer> root = new TreeNode<>();
        binaryTree.setRoot(root);
        root.setData(0);
        root.setIsLeaf(Boolean.FALSE);
        root.setLeft(buildTreeNode(1, new TreeNode<>(2), new TreeNode<>(3)));
        root.setRight(buildTreeNode(4, new TreeNode<>(5), new TreeNode<>(6)));
        return binaryTree;
    }

    private static <T> TreeNode<T> buildTreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        TreeNode<T> node = new TreeNode<>();
        node.setIsLeaf(Boolean.FALSE);
        node.setData(data);
        node.setLeft(left);
        node.setRight(right);
        return node;
    }

}
