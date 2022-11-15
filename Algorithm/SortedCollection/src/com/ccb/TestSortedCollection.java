package com.ccb;

import com.ccb.def.BinarySearchTree;
import com.ccb.def.TreeNode;
import com.ccb.tree.RecursionTraverse;

import java.util.Objects;

public class TestSortedCollection {
    public static void main(String[] args) {
        testBinarySearchTree();
    }

    public static void testBinarySearchTree() {
        TreeNode root = getBST();
        TreeNode rootClone = getBST();
        if (!BinarySearchTree.equals(root, rootClone)) throw new AssertionError();
        if (!BinarySearchTree.check(root)) throw new AssertionError();
        if (Objects.isNull(BinarySearchTree.search(root, 20))) throw new AssertionError();
        if (!Objects.isNull(BinarySearchTree.search(root, 99))) throw new AssertionError();
        root = BinarySearchTree.build(new int[]{24,26,25,30,20,16,15,17});
        if (!BinarySearchTree.check(root)) throw new AssertionError();
        if (Objects.isNull(BinarySearchTree.search(root, 20))) throw new AssertionError();
        if (!Objects.isNull(BinarySearchTree.search(root, 99))) throw new AssertionError();
        RecursionTraverse.inorder(root);
        System.out.println();
        RecursionTraverse.preorder(root);
        if (!BinarySearchTree.equals(root, getBuildBST())) throw new AssertionError();

    }

    /**
     * 获取一颗 二叉搜索树
     *                  24
     *               |      \
     *              15       25
     *                \         \
     *                16        26
     *                  \         \
     *                  17        30
     *                   \
     *                   20
     *
     * @return 树的跟节点
     */
    private static TreeNode getBuildBST() {
        TreeNode root = new TreeNode(24);
        TreeNode node1 = new TreeNode(15);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(16);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node6 = new TreeNode(20);
        TreeNode node7 = new TreeNode(26);
        root.setLeft(node1);
        root.setRight(node5);
        node1.setRight(node3);
        node3.setRight(node4);
        node4.setRight(node6);
        node5.setRight(node7);
        node7.setRight(node2);
        return root;
    }

    /**
     * 获取一颗 二叉搜索树
     *                  20
     *               |      \
     *              16       30
     *             |  \     |   \
     *            15   17  25
     *                    |  \
     *                   24  26
     *
     * @return 树的跟节点
     */
    private static TreeNode getBST() {
        TreeNode root = new TreeNode(20);
        TreeNode node1 = new TreeNode(16);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node6 = new TreeNode(24);
        TreeNode node7 = new TreeNode(26);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node5.setLeft(node6);
        node5.setRight(node7);
        return root;
    }
}
