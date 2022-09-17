package com.ccb.morris;

import com.ccb.def.TreeNode;

/**
 * 线索化树
 * 遍历达到时间复杂度O(n),空间复杂度O(1)
 *
 * 和递归遍历树的区别：
 *  递归，一个节点要经过3次
 *  morris，一个节点，如果有左子节点，经过2次；如果没有左子节点，经过1次
 *
 * 使用：
 *  如果需要左右子树的信息，用递归
 *  如果不需要，morris最省空间
 */
public class Morris {

    /**
     * morris遍历模版：
     *  1、当前节点为空停止
     *  2、判断当前节点的左子节点是否存在
     *      如果不存在，当前节点移动到当前节点的右子节点1⃣️
     *      如果存在，判断左子节点的最右子节点的右指针的值
     *          如果为当前节点，将左子节点的最右叶子节点的右指针改成null，当前节点移动到当前节点的右子节点2⃣️
     *          如果为null，将左子节点的最右叶子节点的右指针改成当前节点，当前节点移动到当前节点的左子节点3⃣️
     *
     *     例：
     *                           12
     *      *                 |      \
     *      *               13       14
     *      *              |  \     |   \
     *      *            15   16  137
     *      *                    |  \
     *      *                   18  10
     *     那么通过morris遍历的过程为：12 13 15 13 16 12 14 137 18 137 10 14
     *                              输出插入点
     *          前序遍历：           1⃣️之前 3⃣️之前【第一次到达就打印】
     *
     *          中序遍历：           1⃣️之前 2⃣️之前【第二次到达再打印】
     *
     *          后序遍历：           第二次到达，逆序打印左子树的右链
     *
     *
     * @param node 节点
     */
    private static void morris(TreeNode node) {
        TreeNode cur;
        while (node != null) {
            if ((cur = node.getLeft()) == null) {
                // 如果当前节点的左子节点不存在
                node = node.getRight();
            } else {
                // 如果存在
                while(cur.getRight() != null && cur.getRight() != node) {
                    cur = cur.getRight();
                }
                if (cur.getRight() == null) {
                    // 如果左子节点的最右叶子节点的右指针值为null
                    cur.setRight(node);
                    node = node.getLeft();
                } else {
                    // 如果左子节点的最右叶子节点的右指针值为当前节点
                    cur.setRight(null);
                    node = node.getRight();
                }
            }
        }
    }

    /**
     * 前序遍历
     * @param node 节点
     */
    public static void preorder(TreeNode node) {
        TreeNode cur;
        while (node != null) {
            if ((cur = node.getLeft()) == null) {
                // 如果当前节点的左子节点不存在
                System.out.print(node.getVal() + " ");
                node = node.getRight();
            } else {
                // 如果存在
                while(cur.getRight() != null && cur.getRight() != node) {
                    cur = cur.getRight();
                }
                if (cur.getRight() == null) {
                    // 如果左子节点的最右叶子节点的右指针值为null
                    System.out.print(node.getVal() + " ");
                    cur.setRight(node);
                    node = node.getLeft();
                } else {
                    // 如果左子节点的最右叶子节点的右指针值为当前节点
                    cur.setRight(null);
                    node = node.getRight();
                }
            }
        }
    }

    /**
     * 中序遍历
     * @param node 节点
     */
    public static void inorder(TreeNode node) {
        TreeNode cur;
        while (node != null) {
            if ((cur = node.getLeft()) == null) {
                // 如果当前节点的左子节点不存在
                System.out.print(node.getVal() + " ");
                node = node.getRight();
            } else {
                // 如果存在
                while(cur.getRight() != null && cur.getRight() != node) {
                    cur = cur.getRight();
                }
                if (cur.getRight() == null) {
                    // 如果左子节点的最右叶子节点的右指针值为null
                    cur.setRight(node);
                    node = node.getLeft();
                } else {
                    // 如果左子节点的最右叶子节点的右指针值为当前节点
                    System.out.print(node.getVal() + " ");
                    cur.setRight(null);
                    node = node.getRight();
                }
            }
        }
    }

    /**
     * 后序遍历
     * @param node 节点
     */
    public static void postorder(TreeNode node) {
        TreeNode root = node;
        TreeNode cur;
        while (node != null) {
            if ((cur = node.getLeft()) == null) {
                // 如果当前节点的左子节点不存在
                node = node.getRight();
            } else {
                // 如果存在
                while(cur.getRight() != null && cur.getRight() != node) {
                    cur = cur.getRight();
                }
                if (cur.getRight() == null) {
                    // 如果左子节点的最右叶子节点的右指针值为null
                    cur.setRight(node);
                    node = node.getLeft();
                } else {
                    // 如果左子节点的最右叶子节点的右指针值为当前节点
                    cur.setRight(null);
                    processLeftAndRightLink(node.getLeft());
                    node = node.getRight();
                }
            }
        }
        // 结尾处理根节点 右链
        processLeftAndRightLink(root);
    }

    /**
     * 处理节点的左子树的右链
     *
     * @param node 节点的左子树起点
     */
    private static void processLeftAndRightLink(TreeNode node) {
        if (node == null) {
            return;
        }

        // 反转一次
        node = reverse(node);

        TreeNode cur = node;
        while(cur != null) {
            // 处理节点
            System.out.print(cur.getVal() + " ");
            cur = cur.getRight();
        }

        // 反转回去
        node = reverse(node);
    }

    /**
     * 反转节点 的 右链
     *
     * @param node 节点
     */
    private static TreeNode reverse(TreeNode node) {
        TreeNode preNode = null;
        TreeNode tmp;
        while (node != null) {
            tmp = node.getRight();
            node.setRight(preNode);
            preNode = node;
            node = tmp;
        }
        return preNode;
    }

    /**
     * Morris应用：
     * 判断树是否是一颗 二叉搜索树
     *
     * @param node 节点
     * @return 是否
     */
    public static boolean isBST(TreeNode node) {
        TreeNode preNode = null;
        TreeNode cur;
        while (node != null) {
            // 每次进行大小判断
            if (preNode != null && preNode.getVal() > node.getVal()) {
                return false;
            }
            if ((cur = node.getLeft()) == null) {
                // 如果当前节点的左子节点不存在
                // 中序遍历处理点1
                preNode = node;
                node = node.getRight();
            } else {
                // 如果存在
                while(cur.getRight() != null && cur.getRight() != node) {
                    cur = cur.getRight();
                }
                if (cur.getRight() == null) {
                    // 如果左子节点的最右叶子节点的右指针值为null
                    cur.setRight(node);
                    node = node.getLeft();
                } else {
                    // 如果左子节点的最右叶子节点的右指针值为当前节点
                    cur.setRight(null);
                    // 中序遍历处理点2
                    preNode = node;
                    node = node.getRight();
                }
            }
        }
        return true;
    }

}
