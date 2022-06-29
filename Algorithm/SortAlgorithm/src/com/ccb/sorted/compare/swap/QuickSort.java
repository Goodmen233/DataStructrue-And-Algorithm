package com.ccb.sorted.compare.swap;

import java.util.Arrays;

/**
 * 快速排序 双指针+递归
 */
public class QuickSort {

    /**
     * 快速排序——升序
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        sortUp(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序——升序，递归
     * @param arr 需要排序的数组
     * @param left 左指针
     * @param right 右指针
     */
    private static void sortUp(int[] arr, int left, int right){
        if(left >= right) return;
        int mid = arr[left];// 所以右边先开始遍历，”坑“
        int l = left;
        int r = right;// 存下标，left，right留着递归使用
        while(l < r){
            while(l < r && arr[r] > mid){
                r--;
            }// 找到右边小于中轴的元素，交换
            arr[l] = arr[r];
            while(l < r && arr[l] < mid){
                l++;
            }// 找到左边大于中轴的元素，交换
            arr[r] = arr[l];
        }
        arr[l] = mid;// 这时，l == r，把之前的中轴值填入
        // 继续递归左右区间
        sortUp(arr, left, l);
        sortUp(arr, l + 1, right);
    }

    /**
     * 快速排序——降序
     * @param arr 需要排序的数组
     */
    public static void sortDown(int[] arr){
        sortDown(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序——降序，递归
     * @param arr 需要排序的数组
     * @param left 左指针
     * @param right 右指针
     */
    private static void sortDown(int[] arr, int left, int right){
        if(left >= right) return;
        int mid = arr[left];// 所以右边先开始遍历，”坑“
        int l = left;
        int r = right;// 存下标，left，right留着递归使用
        while(l < r){
            while(l < r && arr[r] < mid){
                r--;
            }// 找到右边大于中轴的元素，交换
            arr[l] = arr[r];
            while(l < r && arr[l] > mid){
                l++;
            }// 找到左边小于中轴的元素，交换
            arr[r] = arr[l];
        }
        arr[l] = mid;// 这时，l == r，把之前的中轴值填入
        // 继续递归左右区间
        sortDown(arr, left, l);
        sortDown(arr, l + 1, right);
    }

    /**
     * 实现交换
     * @param arr 要交换的数组
     * @param i 交换下标i
     * @param j 交换下标j
     */
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ---------------------------------------new by 2022.06.29---------------------------------------------------------
    public static void sort1(int[] arr){
        sort1(arr, 0, arr.length - 1);
    }

    /**
     * 快排1.0，以最后一个元素num为轴，单指针将区域分为<=num区域, >num区域
     * 1、如果当前值小于等于num，当前值移动到left下一位，left++，当前值的指针++
     * 2、如果当前值大于num，当前值的指针++
     */
    private static void sort1(int[] arr, int start, int end){
        if(start >= end){
            return;
        }
        int num = arr[end];
        // 指针
        int left = start - 1;
        for (int i = start; i <= end; i++) {
            // 1、
            if(arr[i] <= num) {
                swap(arr, ++left, i);
            }
        }
        sort1(arr, start, left - 1);
        sort1(arr, left + 1, end);
    }

    public static void sort2(int[] arr){
        sort2(arr, 0, arr.length - 1);
    }
    /**
     * 快排2.0，以最后一个元素为轴，双指针将区域分成<num, =num, >num区域
     * 1、当前值<num, 当前值和<num区域后一个交换，<num区域右扩，当前值指针++
     * 2、当前值>num, 当前值和>num区域前一个交换，>num区域左扩，当前值指针不变
     * 3、当前值=num，当前值指针++
     * @param arr
     * @param start
     * @param end
     */
    private static void sort2(int[] arr, int start, int end){
        if(start >= end){
            return;
        }
        int num = arr[end];
        // 双指针
        int left = start - 1;
        int right = end + 1;
        for (int i = start; i < right; i++) {
            // 1、
            if(arr[i] < num) {
                swap(arr, ++left, i);
            }else if(arr[i] > num){// 2、
                swap(arr, --right, i--);// i--为了保持指针不变
            }
        }
//        System.out.println(Arrays.toString(arr) + "-left:" + left + "-right:" + right);
        sort2(arr, start, left);
        sort2(arr, right, end);
    }

}
