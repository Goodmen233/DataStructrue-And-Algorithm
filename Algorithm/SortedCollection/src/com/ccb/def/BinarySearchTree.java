package com.ccb.def;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 二叉排序树：
 *  默认没有重复项。如果有，可以封装节点，使之记录出现次数。
 *  一、基础操作：
 *  1、查找、新增：类二分法
 *  2、删除：
 *      1、没有左右子树，直接删
 *      2、只有一边，直接接上去
 *      3、左右子树存在：
 *          选择左子树[右子树]，向右[左]找端点New，代替删除节点。然后将New的左子树[右子树]接到New的位置
 *          概括：找次小或次大代替
 *  3、旋转：
 *      左旋：右子树的左指针指向当前节点，右子树的左孩子为当前节点的右孩子
 *      右旋：左子树的右指针指向当前节点，左子树的右孩子为当前节点的左孩子
 *
 *  二、分类(平衡性判断标准不同)：
 *      1、AVL[高度]：
 *      2、SB树[Size]：
 *      3、红黑树[颜色]：
 *
 *      关系：
 *          BST<左右旋的BST<AVL/红黑树/SB树
 *
 * ------------------------------------------------------------------
 * 以下均用左小右大进行实现
 */
public class BinarySearchTree {

    /**
     * 构建二叉搜索树
     *
     * @param values 搜索树值集合
     * @return 树的根节点
     */
    public static TreeNode build(int[] values) {
        if (values.length <= 0) {
            return new TreeNode();
        }
        Arrays.sort(values);
        // 拿排序后的中间节点作为根节点，保持一定平衡
        TreeNode root = new TreeNode(values[values.length / 2]);
        // 因为add操作遇到相同值直接跳过，所以不做过滤
        for (int value : values) {
            add(root, value);
        }
        return root;
    }

    /**
     * 添加节点
     *
     * @param root 根节点
     * @param value 节点值
     * @return 是否添加成功
     */
    public static boolean add(TreeNode root, Integer value) {
        if (Objects.isNull(root) || !check(root)) {
            return false;
        }
        TreeNode cur = root;
        TreeNode pre = cur;
        int curVal = 0;
        while(! Objects.isNull(cur)) {
            curVal = cur.getVal();
            // 如果值相等，直接跳过
            if (Objects.equals(value, curVal)) {
                return true;
            } else if (curVal > value) {
                pre = cur;
                cur = cur.getLeft();
            } else {
                // curVal < value
                pre = cur;
                cur = cur.getRight();
            }
        }
        // 此时curVal = pre.val
        if (curVal > value) {
            pre.setLeft(new TreeNode(value));
        } else {
            // curVal < value
            pre.setRight(new TreeNode(value));
        }
        return true;
    }

    /**
     * 查找指定值在树上的节点
     *
     * @param root 根节点
     * @param target 目标值
     * @return 目标值的节点，没有返回null
     */
    public static TreeNode search(TreeNode root, Integer target) {
        if (!check(root)) {
            return null;
        }
        TreeNode cur = root;
        while (!Objects.isNull(cur)) {
            if (Objects.equals(target, cur.getVal())) {
                return cur;
            } else if (target < cur.getVal()) {
                cur = cur.getLeft();
            } else {
                // target > cur.getVal()
                cur = cur.getRight();
            }
        }
        return null;
    }

    /**
     * 删除指定值在树上的节点
     *
     * @param root 根节点
     * @param target 目标值
     * @return 删除成功返回true，空或失败返回false
     */
    public static boolean delete(TreeNode root, Integer target) {
        if (!check(root)) {
            return false;
        }
        TreeNode cur = root;
        TreeNode pre = null;
        if (! Objects.equals(cur.getVal(), target)) {
            while (!Objects.isNull(cur)) {
                if (Objects.equals(target, cur.getVal())) {
                    break;
                } else if (target < cur.getVal()) {
                    pre = cur;
                    cur = cur.getLeft();
                } else {
                    pre = cur;
                    // target > cur.getVal()
                    cur = cur.getRight();
                }
            }
        }
        if (Objects.isNull(cur)) {
            return false;
        }
        // 判断删除节点的情况
        // 1、左右子树都为空，直接删除
        // 2、左右子树一者为空，直接接上去。
        if (Objects.isNull(cur.getLeft()) && Objects.isNull(cur.getRight())) {
            setNode(pre, cur, null);
        } else if (Objects.isNull(cur.getLeft())) {
            setNode(pre, cur, cur.getRight());
        } else if (Objects.isNull(cur.getRight())) {
            setNode(pre, cur, cur.getLeft());
        } else {
            // 3、左右子树不为空，找到次大值/次小值，直接替代
            // TODO
        }
        return true;
    }

    /**
     * 替换节点
     *
     * @param pre 替换节点的父节点
     * @param cur 要替换的节点
     * @param instead 替换节点
     */
    private static void setNode(TreeNode pre, TreeNode cur, TreeNode instead) {
        assert pre != null;
        assert cur != null;
        assert instead != null;
        boolean isLeft = Objects.equals(pre.getLeft().getVal(), cur.getVal());
        if (isLeft) {
            pre.setLeft(instead);
        } else {
            pre.setRight(instead);
        }
    }

    /**
     * 检查是否是二叉搜索树
     *
     * @param root 根节点
     * @return 是返回true，空或否返回false
     */
    public static boolean check(TreeNode root) {
        if (Objects.isNull(root)) {
            return false;
        }
        // 中序遍历，前一个值肯定比后一个值小
        TreeNode pre = null;
        // 用通用栈遍历实现
        Deque<TreeNode> stack = new LinkedList();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode topNode = stack.pop();
            if (topNode != null) {
                if (topNode.getRight() != null) {
                    stack.push(topNode.getRight());
                }
                stack.push(topNode);
                stack.push(null);
                if (topNode.getLeft() != null) {
                    stack.push(topNode.getLeft());
                }
            } else {
                TreeNode cur = stack.pop();
                if (!Objects.isNull(pre) && pre.getVal() >= cur.getVal()) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }

    /**
     * 比较两棵树是否相等
     *
     * @param node1 树1
     * @param node2 树2
     */
    public static boolean equals(TreeNode node1, TreeNode node2) {
        if (!Objects.isNull(node1) && !Objects.isNull(node2)) {
            if (Objects.equals(node1.getVal(), node2.getVal())) {
                return equals(node1.getRight(), node2.getRight()) &&
                        equals(node1.getLeft(), node2.getLeft());
            } else {
                return false;
            }
        } else {
            return Objects.isNull(node1) && Objects.isNull(node2);
        }
    }












    /**
     * 平衡二叉树AVL：高度差不超过1
     *  检测时机：每次变化都要自底向上检查
     *  破坏平衡的4种情况：
     *  1、LL：右旋
     *  2、RR：左旋
     *  3、LR：左子树左旋[左子树的右子节点成为顶点]，整体右旋
     *  4、RL：右子树右旋[右子树的左子节点成为顶点]，整体左旋
     */
    static class BalancedBinaryTree {

    }

    /**
     * SB树：每棵叔叔树的大小，不小于任何侄子树(兄弟节点的子树)的大小
     * 检测时机：每次变化时，变化结构的节点进行递归
     * 破坏平衡的4种情况：同上。在旋转完毕后，还要递归改变结构的节点，重新判断(保险)
     */
    static class SizeBalancedTree {

    }

    /**
     * 红黑树
     * 特点：
     *  1、节点 要么黑要么红
     *  2、根节点/叶子节点[空节点]为黑
     *  3、红节点不能相邻
     *  4、当前节点到每一个叶子节点经过的黑节点数量一样
     */
    static class RedBlackTree {

    }

}
