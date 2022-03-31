package com.ccb.sorted.compare.insert;

/**
 * 插入排序，逻辑上看成一个有序列表和一个无序列表，每次从无序列表中的第一个元素找到有序列表的位置
 */
public class InsertSort {
    /**
     * 插入排序——升序，交换法
     * @param arr 要排序的数组
     */
    public static void sortUpSwap(int[] arr){
        int cur = 1;// 假设第一个元素已经在有序列表里，从第二个开始
        while(cur < arr.length){
            for(int i = cur; i > 0; i--){
                if(arr[i] < arr[i - 1]){
                    swap(arr, i, i - 1);// 一直交换，直到到达有序列表相应位置
                }else{
                    break;
                }
            }
            cur++;
        }
    }

    /**
     * 插入排序——升序，插入法
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        for(int cur = 1; cur < arr.length; cur++){// 指向无序列表的第一个元素
            int i = cur - 1;// 指向有序列表的末尾元素
            int val = arr[cur];// 保存无序列表第一个元素值
            while(i >= 0 && arr[i] > val){// 如果有序列表的当前元素大于i指针的值
                arr[i + 1] = arr[i];// 后一个的值等于前一个的值，元素后移
                i--;
            }
            arr[i + 1] = val;// 如果i指向不大于val了，那么后一个元素就是坑，补上
        }
    }

    /**
     * 插入排序——降序，交换法
     * @param arr 要排序的数组
     */
    public static void sortDown(int[] arr){
        int cur = 1;// 假设第一个元素已经在有序列表里，从第二个开始
        while(cur < arr.length){
            for(int i = cur; i > 0; i--){
                if(arr[i] > arr[i - 1]){
                    swap(arr, i, i - 1);// 一直交换，直到到达有序列表相应位置
                }else{
                    break;
                }
            }
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
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
    }
}
