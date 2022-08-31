package com.ccb.def.monotonous.stack;

import java.util.*;

/**
 * 单调栈，主要用来求左右两边的最值问题
 */
public class MonotonousStack<T> {
    Deque<Node> stack;
    List<T> list;
    Comparator<T> cmp;

    /**
     * 构造方法
     * @param list 集合元素
     * @param cmp 比较器
     */
    public MonotonousStack(List<T> list, Comparator<T> cmp) {
        stack = new LinkedList<>();
        list = list;
        cmp = cmp;
    }

    /**
     * 获取单调栈的栈顶对应的元素下标
     * @return 元素下标集合
     */
    public Node peek() {
        return stack.peek();
    }

    /**
     * 弹出单调栈的栈顶对应的元素下标
     * @return 元素下标集合
     */
    public Node pop() {
        return stack.pop();
    }

    /**
     * 放入单调栈，并弹出不合格元素
     * @param pos 元素位置
     * @return 不合格元素集合
     */
    public List<Node> push(Integer pos) {
        List<Node> res = new ArrayList<>(10);
        Integer nodeElement = peek().peek();
        // 如果栈为空，直接加入
        if (stack.isEmpty()) {
            stack.push(new Node(pos));
            return res;
        }
        // 如果栈不为空，且栈顶元素值和该元素比较值小于0时，弹出栈顶元素，继续向下比较
        //                                  ｜
        //                         list[nodeElement] - list[pos] 表示栈顶元素小于当前位置，单调递增栈【从栈顶往里看】
        //                         list[pos] - list[nodeElement] 表示栈顶元素大于当前位置，单调递减栈【从栈顶往里看】
        while(! stack.isEmpty() || cmp.compareTo(nodeElement, pos, list) < 0) {
            res.add(pop());
            nodeElement = peek().peek();
        }
        if (cmp.compareTo(pos, nodeElement, list) == 0) {
            // 如果两个位置元素相等，将pos放入栈顶的Node中
            peek().add(pos);
        } else {
            // 直接放入栈中
            stack.push(new Node(pos));
        }
        return res;
    }

    public interface Comparator<T> {
        /**
         * 比较方法
         * @param pos1 位置1
         * @param pos2 位置2
         * @param list 集合元素
         * @return
         * <0，表示位置1的元素小于位置2
         * =0，表示位置1的元素等于位置2
         * >0，表示位置1的元素大于位置2
         */
        int compareTo(int pos1, int pos2, List<T> list);
    }

    /**
     * 定义栈里面存放的元素-集合的元素的下标，用于解决重复元素的问题
     */
    public static class Node {
        private List<Integer> list = new ArrayList<>(10);
        private int pos = -1;

        /**
         * 无参构造
         */
        public Node() {}

        /**
         * 有参构造
         * @param t 元素
         */
        public Node(Integer t) {
            add(t);
        }

        /**
         * 向集合中增加元素
         * @param t 元素
         */
        public void add(Integer t) {
            pos++;
            list.add(t);
        }

        /**
         * 节点中是否还有元素
         * @return 是否还有元素
         */
        public boolean isEmpty() {
            return pos < 0;
        }

        /**
         * 获取元素
         * @return 元素
         */
        public Integer get() {
            if (list.size() == 0) {
                return null;
            } else {
                Integer t = list.get(pos);
                list.remove(pos);
                pos--;
                return t;
            }
        }

        /**
         * 选择指针指向的元素返回
         * @return 元素
         */
        public Integer peek() {
            if (list.size() == 0) {
                return null;
            } else {
                return list.get(pos);
            }
        }
    }
}
