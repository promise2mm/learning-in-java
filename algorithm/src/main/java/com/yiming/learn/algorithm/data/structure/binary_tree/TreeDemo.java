package com.yiming.learn.algorithm.data.structure.binary_tree;

import com.alibaba.fastjson.JSON;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TreeDemo<T> {

    public static void main(String[] args) {
        BinaryTree<Integer> srcTree = getBinaryTree();
        srcTree.setName("src");

        final BinaryTree copiedTree = copy(srcTree);
        log.warn("{}", JSON.toJSONString(srcTree));
        log.warn("{}", JSON.toJSONString(copiedTree));
        log.warn("{}", Objects.equals(srcTree, copiedTree));

    }

    private static <T> BinaryTree copy(BinaryTree<T> sourceTree) {
        TreeNode<T> root = sourceTree.getRoot();

        BinaryTree<T> targetTree = new BinaryTree<>();
        targetTree.setName("src");

        TreeNode<T> newRoot = new TreeNode<>();
        newRoot.setData(root.getData());
        targetTree.setRoot(newRoot);

        newRoot.setLeft(buildNode(root.getLeft(), DIRECTION.LEFT));
        newRoot.setRight(buildNode(root.getRight(), DIRECTION.RIGHT));
        return targetTree;
    }

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

    enum DIRECTION {
        LEFT, RIGHT;
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
