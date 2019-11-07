package com.yiming.learn.algorithm.data.structure.binary_tree;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TreeDemo<T> {

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = getBinaryTree();
        binaryTree.setName("src");

        final BinaryTree copiedTree = copy(binaryTree);
        log.warn("{}", JSON.toJSONString(binaryTree));
        log.warn("{}", JSON.toJSONString(copiedTree));

    }

    private static <T> BinaryTree copy(BinaryTree<T> sourceTree) {
        final TreeNode<T> root = sourceTree.getRoot();

        BinaryTree<T> binaryTree = new BinaryTree<>();
        binaryTree.setName("target");
        TreeNode<T> newRoot = new TreeNode<>();
        newRoot.setData(root.getData());

        binaryTree.setRoot(newRoot);
        newRoot.setLeft(buildNode(root.getLeft(), true));
        newRoot.setRight(buildNode(root.getRight(), false));
        return binaryTree;
    }

    private static <T> TreeNode<T> buildNode(TreeNode<T> node, Boolean left) {
        if (!node.getIsLeaf()) {
            if (left) {
                node.setLeft(buildNode(node.getLeft(), true));
            } else {
                node.setRight(buildNode(node.getRight(), false));
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

    private static <T> TreeNode<T> buildTreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        TreeNode<T> node = new TreeNode<>();
        node.setIsLeaf(Boolean.FALSE);
        node.setData(data);
        node.setLeft(left);
        node.setRight(right);
        return node;
    }

}
