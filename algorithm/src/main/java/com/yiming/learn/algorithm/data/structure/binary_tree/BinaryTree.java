package com.yiming.learn.algorithm.data.structure.binary_tree;

import lombok.Data;

@Data
public class BinaryTree<T> {

    private String name;

    private TreeNode<T> root;

}
