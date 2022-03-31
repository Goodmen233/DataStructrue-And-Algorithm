package com.ccb.no_recursion_traverse;

import com.ccb.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class NoRecursionTraverse {

    /**
     * 前序遍历
     * @param root 当前节点
     */
    public static void preorder(TreeNode root){
        Deque<TreeNode> stack = new LinkedList();
        stack.push(root);
        while(! stack.isEmpty()){
//            // 简单版：
//            TreeNode node = stack.pop();
//            System.out.print(node.getVal() + " ");
//            if(node.getRight() != null) stack.push(node.getRight());
//            if(node.getLeft() != null) stack.push(node.getLeft());
            // 统一版：
            while(! stack.isEmpty()){
                TreeNode node = stack.pop();
                if(node != null){
                    if(node.getRight() != null) stack.push(node.getRight());
                    if(node.getLeft() != null) stack.push(node.getLeft());
                    stack.push(node);
                    stack.push(null);
                }else{
                    node = stack.pop();
                    System.out.print(node.getVal() + " ");
                }
            }
        }
    }

    /**
     * 中序遍历
     * @param root 当前节点
     */
    public static void inorder(TreeNode root){
        Deque<TreeNode> stack = new LinkedList();
        stack.push(root);
        while(! stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node != null){
                if(node.getRight() != null) stack.push(node.getRight());
                stack.push(node);
                stack.push(null);
                if(node.getLeft() != null) stack.push(node.getLeft());
            }else{
                node = stack.pop();
                System.out.print(node.getVal() + " ");
            }
        }
    }

    /**
     * 后序遍历
     * @param root 当前节点
     */
    public static void postorder(TreeNode root){
        Deque<TreeNode> stack = new LinkedList();
        stack.push(root);
        while(! stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node != null){
                stack.push(node);
                stack.push(null);
                if(node.getRight() != null) stack.push(node.getRight());
                if(node.getLeft() != null) stack.push(node.getLeft());
            }else{
                node = stack.pop();
                System.out.print(node.getVal() + " ");
            }
        }
    }

}
