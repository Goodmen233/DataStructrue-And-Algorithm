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

    //--------------------------------new by 2022.06.29-----------------------------------------------------------------
    /**
     * 堆排序-不限位数
     * 通过前缀和来确定位置
     * 前缀和数组的值对应 最后 进该桶的数应该放的位置->从右往左遍历入桶
     * @param arr 排序的数组
     */
    public static void sort(int[] arr){
        // 1、找到最大值
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        // 2、求最大值的位数
        int digit = 0;
        while(max != 0){
            max /= 10;
            digit++;
        }
        // 3、准备十个桶
        int[] bucket = new int[arr.length];
        int i,j;
        // 4、桶排序，有几位就循环几次
        for(int d = 1; d <= digit; d++){
            int[] count = new int[10];
            // 对所有数对应位数的值进行统计
            for (i = 0; i < arr.length; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 计算前缀和
            for(i = 1; i < 10; i++){
                count[i] += count[i - 1];
            }
            // 从后往前通过前缀和放入桶里面
            for(i = arr.length - 1; i >= 0; i--){
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            // 将桶中的值放入原数组中
            for(i = 0; i < arr.length; i++){
                arr[i] = bucket[i];
            }
        }
    }

    /**
     * 获取数字指定位数上的值
     * @param num 数字
     * @param d 位数
     */
    private static int getDigit(int num, int d){
        int res = 0;
        while(d > 0){
            res = num % 10;
            num /= 10;
            d--;
        }
        return res;
    }
}
