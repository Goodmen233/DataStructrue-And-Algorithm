package com.ccb;

import com.ccb.def.TreeNode;
import com.ccb.no_recursion_traverse.NoRecursionTraverse;
import com.ccb.recursion_traverse.RecursionTraverse;

/**
 * 测试类
 */
public class TestTree {

    public static void main(String[] args) {
        TreeNode root = getTree();
        System.out.println("前序遍历:");
        RecursionTraverse.preorder(root);
        System.out.println();
        NoRecursionTraverse.preorder(root);
        System.out.println();
        System.out.println("中序遍历:");
        RecursionTraverse.inorder(root);
        System.out.println();
        NoRecursionTraverse.inorder(root);
        System.out.println();
        System.out.println("后序遍历:");
        RecursionTraverse.postorder(root);
        System.out.println();
        NoRecursionTraverse.postorder(root);
    }

    /**
     * 获取一个树
     *                  12
     *               |      \
     *              13       14
     *             |  \     |   \
     *            15   16  137
     *                    |  \
     *                   18  10
     * @return 树的根节点
     */
    private static TreeNode getTree(){
        TreeNode root = new TreeNode(12);
        TreeNode node1 = new TreeNode(13);
        TreeNode node2 = new TreeNode(14);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(16);
        TreeNode node5 = new TreeNode(137);
        TreeNode node6 = new TreeNode(18);
        TreeNode node7 = new TreeNode(10);
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
