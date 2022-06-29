package com.ccb.sorted;

import com.ccb.sorted.compare.swap.*;

public class Test {
    public static void main(String[] args) {
//        int[] arr = new int[]{9,8,7,7,6,5,4,3,2,1};
//        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,3,54,6,5};
//        int[] arr = new int[]{9,8,7,6,5,53,2,1};
        int[] arr = {34,65,67,54,7,12,66,66,99,88,1,4,7};
//        BubbleSort.sortUp(arr);
//        BubbleSort.sortDown(arr);
//        QuickSort.sortUp(arr);
//        QuickSort.sortDown(arr);
//        QuickSort.sort1(arr);
        QuickSort.sort2(arr);
//        SelectSort.sortUp(arr);
//        SelectSort.sortDown(arr);
//        InsertSort.sortUp(arr);
//        InsertSort.sortDown(arr);
//        ShellSort.sortDown(arr);
//        ShellSort.sortUp(arr);
//        MergeSort.sortUp(arr);
//        MergeSort.sortDown(arr);
//        HeapSort.sortUp(arr);
//        HeapSort.sortDown(arr);
//        CountingSort.sortUp(arr);
//        CountingSort.sortDown(arr);
//        BucketSort.sortUp(arr);
//        BucketSort.sortDown(arr);
//        RadixSort.sortUp(arr);
//        RadixSort.sortDown(arr);
        soutArr(arr);

//        int[][] arr = {
//                {1,2,3,4,5,6},
//                {34,35,36,57},
//                {23,45,67,89,100},
//                {12,13,14,15,16},
//                {45,46,47,48,49}
//        };
//        System.out.println(KWayMergeSort.sortUp(arr));
    }

    /**
     * 快捷输出数组
     * @param arr 需要输出的数组
     */
    public static void soutArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
