package com.ccb.recursion_traverse;

import com.ccb.def.TreeNode;

/**
 * 前中后三种顺序递归遍历
 */
public class RecursionTraverse {
    /**
     * 前序遍历
     * @param root 当前节点
     */
    public static void preorder(TreeNode root){
        if(root == null) return;
        System.out.print(root.getVal() + " ");
        preorder(root.getLeft());
        preorder(root.getRight());
    }

    /**
     * 中序遍历
     * @param root 当前节点
     */
    public static void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.getLeft());
        System.out.print(root.getVal() + " ");
        inorder(root.getRight());
    }

    /**
     * 后序遍历
     * @param root 当前节点
     */
    public static void postorder(TreeNode root){
        if(root == null) return;
        postorder(root.getLeft());
        postorder(root.getRight());
        System.out.print(root.getVal() + " ");
    }

}

