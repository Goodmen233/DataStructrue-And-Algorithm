package com.ccb.sorted.compare.swap;

/**
 * 冒泡排序，两两相比，每次把最值放后面
 */
public class BubbleSort {

    private static boolean isChange = true;// 用来减少冒泡次数

    /**
     * 冒泡排序——升序排
     * @param arr 需要排序的数组
     */
    public static void sortUp(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){// 要遍历的次数为数组长度-1
            isChange = false;
            for(int j = 1; j < arr.length - i; j++){// 冒泡
                if(arr[j] < arr[j - 1]){
                    swap(arr, j, j - 1);// 交换元素
                    isChange = true;
                }
            }
            if(!isChange) return;// 如果本次没有交换，说明已经排序完成，返回
        }
    }

    /**
     * 冒泡排序——降序
     * @param arr 需要排序的数组
     */
    public static void sortDown(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){// 要遍历的次数
            isChange = false;
            for(int j = 1; j < arr.length - i; j++){// 冒泡
                if(arr[j] > arr[j - 1]){
                    swap(arr, j, j - 1);// 交换元素
                    isChange = true;
                }
            }
            if(!isChange) return;// 如果本次没有交换，说明已经排序完成，返回
        }
    }

    /**
     * 用异或实现交换
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
