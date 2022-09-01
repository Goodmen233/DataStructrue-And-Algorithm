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
        this.list = list;
        this.cmp = cmp;
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
     * 判断栈是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * 放入单调栈，并弹出不合格元素
     * @param pos 元素位置
     * @return 不合格元素集合【最后一个元素是留在栈里的元素】
     */
    public List<Node> push(Integer pos) {
        List<Node> res = new ArrayList<>(10);
        // 如果栈为空，直接加入
        if (stack.isEmpty()) {
            stack.push(new Node(pos));
            return res;
        }
        // 指向栈顶元素的某个代表值
        Integer nodeElement = peek().peek();
        // 如果栈不为空，且栈顶元素值和该元素比较值小于0时，弹出栈顶元素，继续向下比较
        //                                  ｜
        //                         list[nodeElement] - list[pos] 表示栈顶元素小于当前位置，单调递增栈【从栈顶往里看】
        //                         list[pos] - list[nodeElement] 表示栈顶元素大于当前位置，单调递减栈【从栈顶往里看】
        while(cmp.compareTo(nodeElement, pos, list) < 0) {
            res.add(pop());
            if (stack.isEmpty()) {
                nodeElement = -1;
                break;
            }
            nodeElement = peek().peek();
        }
        // 将栈顶元素也放入集合里面，方便外部处理
        res.add(peek());
        if (! stack.isEmpty() && cmp.compareTo(pos, nodeElement, list) == 0) {
            // 如果两个位置元素相等，将pos放入栈顶的Node中
            peek().add(pos);
        } else {
            // 直接放入栈中
            stack.push(new Node(pos));
        }
        return res;
    }

    /**
     * 处理节点信息
     * @param left 左侧满足条件值
     * @param right 右侧满足条件值
     * @param cur 当前值
     */
    public static <T> void dealMessage(T left, T right, T cur) {
        System.out.println(cur + "的左侧满足条件的值为" + (left == null ? "null" : left) +
                "，右侧满足条件的值为" + (right == null ? "null" : right));
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
