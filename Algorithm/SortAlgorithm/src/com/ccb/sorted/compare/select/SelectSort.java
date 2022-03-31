package com.ccb.sorted.compare.select;

/**
 * 选择排序，逻辑上分成一个有序和无序，每次找到最值，放到有序列表中
 */
public class SelectSort {

    /**
     * 选择排序——升序
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        int cur = 0;// 有序列表的末端+1
        int min;// 每次的最小值的位置
        while(cur < arr.length - 1){
            min = cur;
            for (int i = cur; i < arr.length; i++) {// 找到每次无序列表的最小值
                if(arr[i] < arr[min]){
                    min = i;
                }
            }
            swap(arr, cur, min); // 放到有序列表中
            cur++;
        }
    }

    /**
     * 选择排序——降序
     * @param arr 要排序的数组
     */
    public static void sortDown(int[] arr){
        int cur = 0;// 有序列表的末端+1
        int max;// 每次的最大值的位置
        while(cur < arr.length - 1){
            max = cur;
            for (int i = cur; i < arr.length; i++) {// 找到每次无序列表的最小值
                if(arr[i] > arr[max]){
                    max = i;
                }
            }
            swap(arr, cur, max); // 放到有序列表中
            cur++;
        }
    }

    /**
     * 实现交换
     * @param arr 要交换的数组
     * @param i 交换下标i
     * @param j 交换下标j
     */
    private static void swap(int[] arr, int i, int j){
//        arr[j] ^= arr[i];
//        arr[i] ^= arr[j];
//        arr[j] ^= arr[i];// 为什么这里用异或中间元素会变成0？====> 因为min可能等于cur，这时候用异或会变0
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
