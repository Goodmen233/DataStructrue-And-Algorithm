package com.ccb.def.monotonous.deque;

import com.ccb.def.monotonous.cmp.Comparator;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 单调队列，用于求某一个区间内最值问题
 */
public class MonotonousDeque<T> {

    /**
     * 记录下标值
     */
    Deque<Integer> deque;

    /**
     * 集合和比较器
     *  用来定义求最大值还是最小值问题
     */
    List<T> list;
    Comparator<T> cmp;

    /**
     * 构造方法
     * @param list
     * @param cmp
     */
    public MonotonousDeque(List<T> list, Comparator<T> cmp) {
        deque = new LinkedList<>();
        this.list = list;
        this.cmp = cmp;
    }

    /**
     * 查看当前区间内的最值的下标
     */
    public Integer peekFirst() {
        if (deque.isEmpty()) {
            return null;
        }
        return deque.peekFirst();
    }

    /**
     * 弹出队首元素
     * @return
     */
    public Integer pollFirst() {
        return deque.pollFirst();
    }

    /**
     * 弹出队尾元素
     * @return
     */
    public Integer pollLast() {
        return deque.pollLast();
    }

    /**
     * 往队尾放入元素，并弹出不合格元素
     * @param ele 元素
     * @return 不合格元素集合
     */
    public List<Integer> pushLast(Integer ele) {
        List<Integer> res = new ArrayList<>();
        // 如果队列为空，直接加入
        if (deque.isEmpty()) {
            deque.addLast(ele);
            return res;
        }
        // 如果栈不为空，且栈顶元素和该元素比较值小于等于0的时候，弹出，继续往里比较哦
        //                                  ｜
        //                      list[topEle] - list[ele] 表示栈顶元素小于当前位置，单调递增队列【从队尾往里看】
        //                      list[ele] - list[topEle] 表示栈顶元素大于当前位置，单调递减队列【从队尾往里看】
        while(! deque.isEmpty() && cmp.compareTo(deque.peekLast(), ele, list) <= 0) {
            res.add(pollLast());
        }
        deque.addLast(ele);
        return res;
    }

    /**
     * 获取区间内对应的最值
     * @param start 开始位置
     * @param end 结束位置
     * @return 最值
     */
    public T getValue(int start, int end) {
        if (start < 0 && end >= list.size()) {
            throw new RuntimeException("区间越界");
        }
        // 先移动右指针到结束位置
        for (int i = 0; i <= end; i++) {
            pushLast(i);
        }
        // 再移动左指针到开始位置
        for (int i = 0; i < start; i++) {
            if (peekFirst() <= i) {
                pollFirst();
            }
        }
        return list.get(peekFirst());
    }

}
