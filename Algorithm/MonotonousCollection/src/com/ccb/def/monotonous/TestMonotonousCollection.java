package com.ccb.def.monotonous;

import com.ccb.def.monotonous.deque.MonotonousDeque;
import com.ccb.def.monotonous.stack.MonotonousStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMonotonousCollection {
    public static void main(String[] args) {
//        maxStackTest();
//        minStackTest();
//        maxDequeTest();
        minDequeTest();
    }

    /**
     * check
     */
    public static void minDequeTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(9,8,4,5,3,2,7,8,6,3,9));
        MonotonousDeque<Integer> deque = new MonotonousDeque<>(list,
                (pos1, pos2, list1) -> list1.get(pos2) - list1.get(pos1));
        // 求区间start到end的最小值
        int start = 1;
        int end = 6;
        System.out.println(deque.getValue(start, end));
    }

    /**
     * check
     */
    public static void maxDequeTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(9,8,4,5,3,2,7,8,6,3,9));
        MonotonousDeque<Integer> deque = new MonotonousDeque<>(list,
                (pos1, pos2, list1) -> list1.get(pos1) - list1.get(pos2));
        // 求区间start到end的最大值
        int start = 1;
        int end = 6;
        System.out.println(deque.getValue(start, end));
    }

    /**
     * check
     */
    public static void maxStackTest() {
        // 求左右两边最近的比他大的数
        List<Integer> list = new ArrayList<>(Arrays.asList(2,4,9,5,5,6,8,1,7,7,9,5));
        MonotonousStack<Integer> stack = new MonotonousStack<>(list,
                (pos1, pos2, list1) -> list1.get(pos1) - list1.get(pos2));
        for (int i = 0 ; i < list.size(); i++) {
            // 依次放入，处理弹出的元素
            List<MonotonousStack.Node> nodes = stack.push(i);
            // 因为集合最后一个元素是位于栈顶的元素【没有弹出】，所以不做处理
            for (int j = 0; j < nodes.size() - 1; j++) {
                MonotonousStack.Node node = nodes.get(j);
                // 将node里面的位置拿出来，依次处理
                // 1、左侧就是当前位置+1的对应节点对应的元素值
                // 2、右侧就是当前放入元素对应节点对应的元素值
                while(node.peek() != null) {
                    MonotonousStack.dealMessage(nodes.get(j + 1) == null ? null : list.get(nodes.get(j + 1).peek()),
                            list.get(i),
                            list.get(node.get()));
                }
            }
        }
        // 处理栈中剩下的元素，元素特点：
        // 1、没有右侧符合条件值
        // 2、左侧就是位于栈中元素下方的元素
        while(! stack.isEmpty()) {
            MonotonousStack.Node node = stack.pop();
            while(node.peek() != null) {
                MonotonousStack.dealMessage(stack.peek() == null ? null : list.get(stack.peek().peek()),
                        null,
                        list.get(node.get()));
            }
        }
    }

    /**
     * check
     */
    public static void minStackTest() {
        // 求左右两边最近的比他小的数
        List<Integer> list = new ArrayList<>(Arrays.asList(5,4,9,5,5,6,8,1,7,7,9,5,8));
        MonotonousStack<Integer> stack = new MonotonousStack<>(list,
                (pos1, pos2, list1) -> list1.get(pos2) - list1.get(pos1));
        for (int i = 0 ; i < list.size(); i++) {
            List<MonotonousStack.Node> nodes = stack.push(i);
            for (int j = 0; j < nodes.size() - 1; j++) {
                MonotonousStack.Node node = nodes.get(j);
                while(node.peek() != null) {
                    MonotonousStack.dealMessage(nodes.get(j + 1) == null ? null : list.get(nodes.get(j + 1).peek()),
                            list.get(i),
                            list.get(node.get()));
                }
            }
        }
        // 处理栈中剩下的元素
        while(! stack.isEmpty()) {
            MonotonousStack.Node node = stack.pop();
            while(node.peek() != null) {
                MonotonousStack.dealMessage(stack.peek() == null ? null : list.get(stack.peek().peek()),
                        null,
                        list.get(node.get()));
            }
        }
    }
}
