package com.ccb.def;

/**
 * 二叉树节点
 */
public class TreeNode {

    /**
     * 值
     */
    private int val;

    /**
     * 左子树
     */
    private TreeNode left;

    /**
     * 右子树
     */
    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

}
