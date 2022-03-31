package com.ccb.sorted.compare.insert;

/**
 * 希尔排序（缩小增量）， 每次找一个增量（每次原增量/2），分组的进行插入排序
 */
public class ShellSort {
    /**
     * 希尔排序——升序
     * @param arr 要排序的数组
     */
    public static void sortUp(int[] arr){
        int inc = arr.length / 2;
        while(inc > 0){// 每次/2来缩小增量
            // ---------------相当于插入排序，只不过是有分组的插入排序
            for(int i = inc; i < arr.length; i++){// 不按每次增量分组来，而是交叉进行
                int val = arr[i];// 存储当前值
                int cur = i - inc;// 存储有序列表的最后一个元素
                while(cur >= 0 && arr[cur] > val){
                    arr[cur + inc] = arr[cur]; // 向后移
                    cur -= inc;
                }
                arr[cur + inc] = val;// 插入到合适的位置
            }
            inc /= 2;
        }
    }

    /**
     * 希尔排序——降序
     * @param arr 要排序的数组
     */
    public static void sortDown(int[] arr){
        int inc = arr.length / 2;
        while(inc > 0){// 每次/2来缩小增量
            // ---------------相当于插入排序，只不过是有分组的插入排序
            for(int i = inc; i < arr.length; i++){// 不按每次增量分组来，而是交叉进行
                int val = arr[i];// 存储当前值
                int cur = i - inc;// 存储有序列表的最后一个元素
                while(cur >= 0 && arr[cur] < val){
                    arr[cur + inc] = arr[cur]; // 向后移
                    cur -= inc;
                }
                arr[cur + inc] = val;// 插入到合适的位置
            }
            inc /= 2;
        }
    }
}
