package com.ccb.sorted.nocompare;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * 计数排序——对每个数进行计数，放进”桶“，然后遍历桶
 * 这里用TreeMap来代替桶
 * 最好是有确定范围的整数,用TreeMap不用考虑这个问题
 */
public class CountingSort {

    /**
     * 计数排序——升序
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        TreeMap<Integer, Integer> bucket = new TreeMap<>();
        // 记录每个元素出现的次数
        for (int i = 0; i < arr.length; i++) {
            bucket.put(arr[i], bucket.getOrDefault(arr[i], 0) + 1);
        }
        // 获取所有的key
        Set<Integer> keys = bucket.keySet();
        int cur = 0;
        // 遍历key
        Iterator<Integer> it = keys.iterator();
        while(it.hasNext()){
            int key = it.next();
            while(bucket.get(key) != 0){
                arr[cur++] = key;
                bucket.put(key, bucket.get(key) - 1);
            }
        }
    }

    /**
     * 计数排序——降序
     * @param arr 要排序的数组
     */
    public static void sortDown(int[] arr){
        TreeMap<Integer, Integer> bucket = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 记录每个元素出现的次数
        for (int i = 0; i < arr.length; i++) {
            bucket.put(arr[i], bucket.getOrDefault(arr[i], 0) + 1);
        }
        // 获取所有的key
        Set<Integer> keys = bucket.keySet();
        int cur = 0;
        // 遍历key
        Iterator<Integer> it = keys.iterator();
        while(it.hasNext()){
            int key = it.next();
            while(bucket.get(key) != 0){
                arr[cur++] = key;
                bucket.put(key, bucket.get(key) - 1);
            }
        }
    }
}
