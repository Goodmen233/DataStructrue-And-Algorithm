package com.ccb.sorted.compare.swap;

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
}
