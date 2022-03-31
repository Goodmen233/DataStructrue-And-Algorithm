package com.ccb.sorted.compare.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * k路归并——外部排序
 * 结构：
 *      ls[]
 *     |    \
 *    b[]   b[]
 */
public class KWayMergeSort {
    private static int k = 2;// k路

    /**
     * k路归并——升序
     * @param arr 要排序的数组，要求每个子数组已经是升序排列
     *            arr.length = k路
     *            子数组即为数据
     */
    public static List<Integer> sortUp(int[][] arr){
        List<Integer> res = new ArrayList<>(10);
        k = arr.length;

        // 1、创建index数组，来索引每个arr子数组当前排序的位置
        int[] index = new int[k];
        Arrays.fill(index, 0);

        // 2、创建b数组来存储每一路的未排序的首元素, b[k]存放最小值
        int[] b = new int[k + 1];
        for(int i = 0; i < k; i++){
            b[i] = arr[i][index[i]];
        }
        b[k] = Integer.MIN_VALUE;

        // 3、创建败者树
        int[] ls = new int[k];
        Arrays.fill(ls, k);// 先假设每个非叶子节点的值为k，也就是失败者b[k] = 最小值
        // 通过b来构建败者树
        for(int i = k - 1; i >= 0; i--){
            adjust(b, ls, i);
        }

        // 每次获取胜利者加到结果集，并重新调整
        while(b[ls[0]] != Integer.MAX_VALUE){
            // 获取胜利者的索引
            int victory = ls[0];
            res.add(b[victory]);
            // 修改索引指向下一个位置
            index[victory]++;
            // 判断是否读完
            if(index[victory] < arr[victory].length){
                // 没有读完，读取下一个元素
                b[victory] = arr[victory][index[victory]];
            }else{
                // 读完，赋值最大值表示读完
                b[victory] = Integer.MAX_VALUE;
            }
            // 调整败者树
            adjust(b, ls, victory);
        }

        return res;
    }

    /**
     * 调整败者树
     * @param b 当前排序的叶子节点
     * @param ls 败者树
     * @param i 添加的叶子节点
     */
    private static void adjust(int[] b, int[] ls, int i) {
        int parent = (i + k) / 2;// 获得父节点,因为i从叶子节点开始索引，所以加上非叶子节点k再/2才是父节点的位置
        while(parent > 0){
            if(b[i] > b[ls[parent]]){// 与父节点指向的数据比较
                int temp = i;
                i = ls[parent];
                ls[parent] = temp;
                // ls[parent]记录失败者，i指向新的胜利者
            }
            parent /= 2;// 去上个父节点
        }
        ls[0] = i;// 记录胜利者
    }



}
