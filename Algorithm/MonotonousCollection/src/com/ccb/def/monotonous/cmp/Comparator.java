package com.ccb.def.monotonous.cmp;


import java.util.List;

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