package com.ccb.sorted.nocompare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 基数排序，每次按位数来排序
 */
public class RadixSort {

    /**
     * 基数排序——升序
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        // 先找出最大值的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(max < arr[i]) max = arr[i];
        }
        int len = (max + "").length();// 总位数
        int mod = 10;// 求对应位数的因子
        int div = 1;
        List<Integer>[] bucket = new ArrayList[10];// 桶
        // 从个位开始，依次排序
        for(int i = 0; i < len; i++){
            // 遍历元素
            for (int j = 0; j < arr.length; j++) {
                int pos = (arr[j] % mod) / div;// 求得对应位的值
                if(bucket[pos] == null) bucket[pos] = new ArrayList<>();
                bucket[pos].add(arr[j]);// 放入桶里
            }
            int cur = 0;
            // 将桶里的元素依次拿出来
            for(int y = 0; y < bucket.length; y++){
                List<Integer> temp = bucket[y];
                if(temp != null){
                    Collections.sort(temp);
                    for (int x = 0; x < temp.size(); x++) {
                        arr[cur++] = temp.get(x);
                    }
                    // 清空桶，下次继续使用
                    temp.clear();
                }
            }
            // 求对应位的数
            mod *= 10;
            div *= 10;
        }
    }

    /**
     * 基数排序——降序
     * @param arr 要排序的数组
     */
    public static void sortDown(int[] arr){
        // 先找出最大值的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(max < arr[i]) max = arr[i];
        }
        int len = (max + "").length();// 总位数
        int mod = 10;// 求对应位数的因子
        int div = 1;
        List<Integer>[] bucket = new ArrayList[10];// 桶
        // 从个位开始，依次排序
        for(int i = 0; i < len; i++){
            // 遍历元素
            for (int j = 0; j < arr.length; j++) {
                int pos = (arr[j] % mod) / div;// 求得对应位的值
                if(bucket[pos] == null) bucket[pos] = new ArrayList<>();
                bucket[pos].add(arr[j]);// 放入桶里
            }
            int cur = 0;
            // 将桶里的元素依次拿出来
            for(int y = bucket.length - 1; y >= 0; y--){
                List<Integer> temp = bucket[y];
                if(temp != null){
                    Collections.sort(temp);
                    for (int x = temp.size() - 1; x >= 0; x--) {
                        arr[cur++] = temp.get(x);
                    }
                    // 清空桶，下次继续使用
                    temp.clear();
                }
            }
            // 求对应位的数
            mod *= 10;
            div *= 10;
        }
    }
}
