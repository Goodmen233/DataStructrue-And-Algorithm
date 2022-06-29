package com.ccb.sorted.nocompare;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序，利用函数映射将每个数映射到对应桶里，然后按顺序遍历桶
 * 每个桶要么在插入时候排序，要么每次访问桶时直接排序，后者效率比较好
 */
public class BucketSort {

    /**
     * 桶排序——升序
     * 数的范围在 0~99 之间
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        List<Integer>[] bucket = new ArrayList[10];
        // 将数组中的每个元素放到桶里，这里的映射关系为pos = arr[i] / 10
        for (int i = 0; i < arr.length; i++) {
            int pos = arr[i] / 10;
            if(bucket[pos] == null) bucket[pos] = new ArrayList<>();
            bucket[pos].add(arr[i]);
        }
        int cur = 0;
        // 遍历每个桶中的元素，依次放入arr
        for (int i = 0; i < bucket.length; i++) {
            List<Integer> temp = bucket[i];
            if(temp != null){
                Collections.sort(temp);
                for (int j = 0; j < temp.size(); j++) {
                    arr[cur++] = temp.get(j);
                }
            }

        }
    }

    /**
     * 桶排序——降序
     * 数的范围在 0~99 之间
     * @param arr 要排序的数组
     */
    public static void sortDown(int[] arr){
        List<Integer>[] bucket = new ArrayList[10];
        // 将数组中的每个元素放到桶里，这里的映射关系为pos = arr[i] / 10
        for (int i = 0; i < arr.length; i++) {
            int pos = arr[i] / 10;
            if(bucket[pos] == null) bucket[pos] = new ArrayList<>();
            bucket[pos].add(arr[i]);
        }
        int cur = 0;
        // 遍历每个桶中的元素，依次放入arr
        for (int i = bucket.length - 1; i >= 0; i--) {
            List<Integer> temp = bucket[i];
            if(temp != null){
                Collections.sort(temp);
                for (int j = temp.size() - 1; j >= 0; j--) {
                    arr[cur++] = temp.get(j);
                }
            }

        }
    }
}
