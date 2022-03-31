package com.ccb.sorted.compare.merge;

/**
 * 分而治之
 * 二路归并排序， 递归二分，依次排序
 */
public class MergeSort {
    private static boolean isUp = true;

    /**
     * 归并排序——升序
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        isUp = true;
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序——降序
     * @param arr 要排序的数组
     */
    public static void sortDown(int[] arr){
        isUp = false;
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 先把数组一分为二，然后按顺序合并
     * @param arr 要排序的数组
     * @param left 分的起始点
     * @param right 分的末尾点
     */
    private static void mergeSort(int[] arr, int left, int right){
        if(left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        if(isUp){
            mergeUp(arr, left , mid, right);
        }else{
            mergeDown(arr, left, mid, right);
        }
    }

    /**
     * 按递减的顺序合并起来
     * @param arr 要排序的数组
     * @param left 左边开始下标
     * @param mid 左边结束下标
     * @param right 右边结束下标
     */
    private static void mergeDown(int[] arr, int left, int mid, int right) {
        int[] sorted = new int[right - left + 1];
        int cur = 0;
        int leftStart = left;
        int rightStart = mid + 1;
        // 先从左右依次找最小值放入sorted数组中
        while(leftStart <= mid && rightStart <= right){
            if(arr[leftStart] > arr[rightStart]){
                sorted[cur++] = arr[leftStart++];
            }else{
                sorted[cur++] = arr[rightStart++];
            }
        }
        // 如果左边遍历完，把右边剩下的赋值给排序数组
        if(leftStart > mid){
            while(rightStart <= right){
                sorted[cur++] = arr[rightStart++];
            }
        }
        // 如果右边遍历完， 把左边剩下的赋值给排序数组
        if(rightStart > right){
            while(leftStart <= mid){
                sorted[cur++] = arr[leftStart++];
            }
        }
        // 将排序数组拷贝到原数组里
        System.arraycopy(sorted, 0, arr, left, right - left + 1);
    }

    /**
     * 按递增的顺序合并起来
     * 仿照java的Arrays.sort的源码改进----------------------------------------------------------------------------
     * @param arr 要排序的数组
     * @param left 左边开始下标
     * @param mid 左边结束下标
     * @param right 右边结束下标
     */
    private static void mergeUp(int[] arr, int left, int mid, int right) {
        // 如果左边的最后一个比右边的第一个小，那就不需要继续排了
        if(arr[mid] < arr[mid + 1]){
            return;
        }
        int[] sorted = new int[right - left + 1];// 用来放排序的数组
        // 将左右列表的最小数依次入排序数组中
        for(int i = 0, p = left, q = mid + 1; i < sorted.length; i++) {// i为排序数组下标，p为左边列表指针，q为右边列表指针
            if (q > right || p <= mid && arr[p] < arr[q])// 如果右边列表遍历完 或者 左边列表还没遍历完且左边当前值小于右边当前值
                sorted[i] = arr[p++];
            else
                sorted[i] = arr[q++];
        }
        // 将排序数组拷贝到原数组里
        System.arraycopy(sorted, 0, arr, left, right - left + 1);
    }
}
