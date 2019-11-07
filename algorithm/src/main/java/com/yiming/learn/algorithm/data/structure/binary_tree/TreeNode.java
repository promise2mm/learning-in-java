package com.yiming.learn.algorithm.data.structure.binary_tree;

import lombok.Data;

@Data
public class TreeNode<T> {

    private TreeNode<T> left;

    private TreeNode<T> right;

    private T data;

    private Boolean isLeaf = Boolean.FALSE;

    public TreeNode(T data) {
        this.data = data;
        this.isLeaf = true;
        this.left = null;
        this.right = null;
    }

    public TreeNode() {

    }

}
