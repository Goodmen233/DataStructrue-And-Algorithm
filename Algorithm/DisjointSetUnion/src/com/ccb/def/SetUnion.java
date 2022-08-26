package com.ccb.def;

import java.util.*;

/**
 * 并查集结构定义
 */
public class SetUnion<T> {
    /**
     * 元素指向哪一个元素
     */
    Map<T, T> pointMap;
    /**
     * 代表元素所在集合的元素个数
     * 用来合并的时候将小集合放入大集合【减小路径压缩的量】
     */
    Map<T, Integer> countMap;

    /**
     * 构造方法
     * @param collection 集合
     */
    public SetUnion(Collection<T> collection) {
        this.pointMap = new HashMap<>(16);
        this.countMap = new HashMap<>(16);
        for (T t : collection) {
            pointMap.put(t, t);
            countMap.put(t, 1);
        }
    }

    /**
     * 获得元素的代表元素
     * @param e 元素
     * @return 代表元素
     */
    public T getHead(T e) {
        Deque<T> stack = new LinkedList<>();
        while(e != pointMap.get(e)) {
            stack.push(e);
            e = pointMap.get(e);
        }
        // 路径压缩
        while(! stack.isEmpty()) {
            pointMap.put(stack.pop(), e);
        }
        return e;
    }

    /**
     * 判断两个元素是否在同一个集合里
     * @param e1 元素1
     * @param e2 元素2
     * @return 是否同一集合
     */
    public boolean isSameSet(T e1, T e2) {
        if(pointMap.containsKey(e1) && pointMap.containsKey(e2)) {
            return getHead(e1) == getHead(e2);
        }
        return false;
    }

    /**
     * 合并两个元素所在的集合
     * @param e1 元素1
     * @param e2 元素2
     */
    public void mergeSet(T e1, T e2) {
        if (! isSameSet(e1, e2)) {
            T head1 = getHead(e1);
            T head2 = getHead(e2);
            Integer size1 = countMap.get(head1);
            Integer size2 = countMap.get(head2);
            // 将小集合放入大集合
            T max = size1 > size2 ? head1 : head2;
            T min = head1 == max ? head2 : head1;
            pointMap.put(min, max);
            countMap.put(max, size1 + size2);
            countMap.remove(min);
        }
    }

}
