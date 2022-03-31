package com.ccb.sorted.compare.select;

/**
 * 堆排序，每次把堆顶（最值）放到数组后面
 */
public class HeapSort {

    /**
     * 堆排序——升序
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        // 构建大顶堆
        for(int i = arr.length / 2 - 1; i >= 0; i--){
            heapifyPartUp(arr, i, arr.length - 1);
        }
        // 每次把堆顶元素和末尾元素交换，并重新堆化
        for(int i = arr.length - 1; i > 0; i--){
            swap(arr, 0, i);
            heapifyPartUp(arr, 0, i - 1);
        }
    }

    /**
     * 局部调整堆为大顶堆,然后递归往下堆化
     * @param arr 要排序的数组
     * @param root 局部的根节点
     * @param len 当前构建堆的临界区域
     */
    private static void heapifyPartUp(int[] arr, int root, int len){
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int largestPos = root;
        // 如果左节点大于largestPos，largestPos等于左节点的位置
        if(left <= len && arr[left] > arr[largestPos]){
            largestPos = left;
        }
        // 如果右节点大于largestPos，largestPos等于右节点的位置
        if(right <= len && arr[right] > arr[largestPos]){
            largestPos = right;
        }
        // 此时如果largestPos不等于root，那么将largestPos的值与root交换，并对largestPos的局部堆重新堆化
        if(largestPos != root){
            swap(arr, largestPos, root);
            heapifyPartUp(arr, largestPos, len);
        }
    }

    /**
     * 堆排序——降序
     * @param arr 要排序的数组
     */
    public static void sortDown(int[] arr){
        // 构建小顶堆
        for(int i = (arr.length - 3) / 2; i >= 0; i--){// (arr.length - 3) / 2 让右节点对应的是数组中的最后一个元素
            heapifyPartDown(arr, i, arr.length - 1);
        }
        // 每次把堆顶元素和末尾元素交换
        for(int i = arr.length - 1; i > 0; i--){
            swap(arr, 0, i);
            heapifyPartDown(arr, 0, i - 1);
        }
    }

    /**
     * 局部调整堆为小顶堆,然后递归往下堆化
     * @param arr 要排序的数组
     * @param root 局部的根节点
     * @param len 当前构建堆的临界区域
     */
    private static void heapifyPartDown(int[] arr, int root, int len){
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int largestPos = root;
        // 如果左节点大于largestPos，largestPos等于左节点的位置
        if(left <= len && arr[left] < arr[largestPos]){
            largestPos = left;
        }
        // 如果右节点大于largestPos，largestPos等于右节点的位置
        if(right <= len && arr[right] < arr[largestPos]){
            largestPos = right;
        }
        // 此时如果largestPos不等于root，那么将largestPos的值与root交换，并对largestPos的局部堆重新堆化
        if(largestPos != root){
            swap(arr, largestPos, root);
            heapifyPartDown(arr, largestPos, len);
        }
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
}
