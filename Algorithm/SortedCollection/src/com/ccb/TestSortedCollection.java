package com.ccb;

import com.ccb.def.BinarySearchTree;
import com.ccb.def.TreeNode;
import com.ccb.tree.RecursionTraverse;

import java.math.BigDecimal;
import java.util.Objects;

public class TestSortedCollection {

    public static void main(String[] args) {
        testBinarySearchTree();
    }

    public static void testBinarySearchTree() {
        TreeNode root = getBST();
        TreeNode rootClone = getBST();
        assertCond(BinarySearchTree.equals(root, rootClone));
        assertCond(BinarySearchTree.check(root));
        assertCond(!Objects.isNull(BinarySearchTree.search(root, 20)));
        assertCond(Objects.isNull(BinarySearchTree.search(root, 99)));
        root = BinarySearchTree.build(new int[]{24,26,25,30,20,16,15,17});
        assertCond(BinarySearchTree.check(root));
        assertCond(!Objects.isNull(BinarySearchTree.search(root, 20)));
        assertCond(Objects.isNull(BinarySearchTree.search(root, 99)));
        RecursionTraverse.inorder(root);
        System.out.println();
        RecursionTraverse.preorder(root);
        System.out.println();
        assertCond(BinarySearchTree.equals(root, getBuildBST()));

        // 1、删除节点，节点是叶子节点
        TreeNode tree = getBuildBST();
        assertCond(BinarySearchTree.delete(tree, 20));
        assertCond(!BinarySearchTree.delete(tree, 100));
        assertCond(BinarySearchTree.equals(tree, getBuildBSTWhenDelete20()));
        // 2、删除节点，节点是只有一边的节点
        tree = getBuildBST();
        assertCond(BinarySearchTree.delete(tree, 15));
        assertCond(BinarySearchTree.equals(tree, getBuildBSTWhenDelete15()));
        // 3、删除节点，节点是左右子树都存在的节点且删除节点的左子树的右子树不为空
        tree = getBST0();
        assertCond(BinarySearchTree.delete(tree, 30));
        assertCond(BinarySearchTree.equals(tree, getBST0WhenDelete30()));
        // 4、删除节点，节点是左右子树都存在的节点且删除节点的左子树的右子树为空
        tree = getBST1();
        assertCond(BinarySearchTree.delete(tree, 30));
        assertCond(BinarySearchTree.equals(tree, getBST1WhenDelete30()));

        tree = getBST0();
        assertCond(BinarySearchTree.equals(BinarySearchTree.leftRotate(tree), getBST0WhenLeftRotate()));
        tree = getBST0();

        assertCond(BinarySearchTree.equals((tree = BinarySearchTree.rightRotate(tree)), getBST0WhenRightRotate()));
        assertCond(BinarySearchTree.equals(BinarySearchTree.leftRotate(tree), getBST0()));
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
     *                  24
     *               |      \
     *              16       25
     *                \         \
     *                17        26
     *                  \         \
     *                  20        30
     *
     * @return 树的跟节点
     */
    private static TreeNode getBuildBSTWhenDelete15() {
        TreeNode root = new TreeNode(24);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(16);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node6 = new TreeNode(20);
        TreeNode node7 = new TreeNode(26);
        root.setLeft(node3);
        root.setRight(node5);
        node3.setRight(node4);
        node4.setRight(node6);
        node5.setRight(node7);
        node7.setRight(node2);
        return root;
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
     *
     *
     * @return 树的跟节点
     */
    private static TreeNode getBuildBSTWhenDelete20() {
        TreeNode root = new TreeNode(24);
        TreeNode node1 = new TreeNode(15);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(16);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node7 = new TreeNode(26);
        root.setLeft(node1);
        root.setRight(node5);
        node1.setRight(node3);
        node3.setRight(node4);
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

    /**
     * 获取一颗 二叉搜索树
     *                  20
     *               |      \
     *              16       30
     *             |  \     |   \
     *            15   17  25   31
     *                    |  \
     *                   24  26
     *
     * @return 树的跟节点
     */
    private static TreeNode getBST0() {
        TreeNode root = new TreeNode(20);
        TreeNode node1 = new TreeNode(16);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node6 = new TreeNode(24);
        TreeNode node7 = new TreeNode(26);
        TreeNode node8 = new TreeNode(31);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node8);
        node5.setLeft(node6);
        node5.setRight(node7);
        return root;
    }

    /**
     * 获取一颗 二叉搜索树
     *                  16
     *               |      \
     *              15      20
     *                      |   \
     *                     17   30
     *                         |  \
     *                        25  31
     *                       |  \
     *                      24  26
     *
     * @return 树的跟节点
     */
    private static TreeNode getBST0WhenRightRotate() {
        TreeNode root = new TreeNode(20);
        TreeNode node1 = new TreeNode(16);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node6 = new TreeNode(24);
        TreeNode node7 = new TreeNode(26);
        TreeNode node8 = new TreeNode(31);
        root.setLeft(node4);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(root);
        node2.setLeft(node5);
        node2.setRight(node8);
        node5.setLeft(node6);
        node5.setRight(node7);
        return node1;
    }

    /**
     * 获取一颗 二叉搜索树
     *                  30
     *                |     \
     *               20      31
     *             |    \
     *            16     25
     *           | \     |\
     *          15 17   24 26
     *
     * @return 树的跟节点
     */
    private static TreeNode getBST0WhenLeftRotate() {
        TreeNode root = new TreeNode(20);
        TreeNode node1 = new TreeNode(16);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node6 = new TreeNode(24);
        TreeNode node7 = new TreeNode(26);
        TreeNode node8 = new TreeNode(31);
        node2.setLeft(root);
        node2.setRight(node8);
        root.setRight(node5);
        root.setLeft(node1);
        node1.setLeft(node3);
        node1.setRight(node4);
        node5.setLeft(node6);
        node5.setRight(node7);
        return node2;
    }

    /**
     * 获取一颗 二叉搜索树
     *                  20
     *               |      \
     *              16       26
     *             |  \     |   \
     *            15   17  25   31
     *                    |
     *                   24
     *
     * @return 树的跟节点
     */
    private static TreeNode getBST0WhenDelete30() {
        TreeNode root = new TreeNode(20);
        TreeNode node1 = new TreeNode(16);
        TreeNode node2 = new TreeNode(26);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node6 = new TreeNode(24);
        TreeNode node8 = new TreeNode(31);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node8);
        node5.setLeft(node6);
        return root;
    }

    /**
     * 获取一颗 二叉搜索树
     *                  20
     *               |      \
     *              16       30
     *             |  \     |   \
     *            15   17  25   31
     *                    |
     *                   24
     *
     * @return 树的跟节点
     */
    private static TreeNode getBST1() {
        TreeNode root = new TreeNode(20);
        TreeNode node1 = new TreeNode(16);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(25);
        TreeNode node6 = new TreeNode(24);
        TreeNode node8 = new TreeNode(31);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node8);
        node5.setLeft(node6);
        return root;
    }


    /**
     * 获取一颗 二叉搜索树
     *                  20
     *               |      \
     *              16       25
     *             |  \     |   \
     *            15   17  24   31
     *
     * @return 树的跟节点
     */
    private static TreeNode getBST1WhenDelete30() {
        TreeNode root = new TreeNode(20);
        TreeNode node1 = new TreeNode(16);
        TreeNode node2 = new TreeNode(25);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(17);
        TreeNode node5 = new TreeNode(24);
        TreeNode node8 = new TreeNode(31);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node8);
        return root;
    }


    /**
     * 断言，条件为假抛异常
     *
     * @param cond 条件
     */
    public static void assertCond (boolean cond) {
        if (!cond) {
            throw new AssertionError();
        }
    }
}
