package com.ccb.kmp;

import java.util.Arrays;

/**
 * 字符串KMP匹配算法
 * 思想：动态规划优化回溯，状态转移图，从之前状态推出现在状态
 */
public class Kmp {

    /**
     * kmp算法——字符回退位置往后移动一位
     * @param txt 需要找的字符串
     * @param pattern 要匹配的字符串
     * @return 如果存在返回下标，不存在返回-1
     */
    public static int kmp(String txt, String pattern){
        // 1、根据pattern构造next数组
        int[] next = new int[pattern.length()];// next[i]表示如果i位置不匹配，从next[i]位置重新匹配
        int i = 0;// 指向后缀末尾
        int j = -1;// 指向前缀末尾
        next[0] = j;
        while(i < pattern.length() - 1){
            if(j == -1 || pattern.charAt(i) == pattern.charAt(j)){
                // 如果是i字符和j字符相等，
                //      那么到时候如果pattern[i+1]匹配不上，就跳回pattern[j+1]处重新比较
                // 如果是j = -1的情况，那么就是前缀和后缀毫无匹配关系，只能重头来。
                //      那么到时候如果pattern[i+1]匹配不上，就跳回pattren[0]开始了。
                i++;
                j++;
                next[i] = j;
            }else{
                // 如果匹配不上，且还没回退到j = -1时， 就一直回退，直到匹配或者j=-1时才停止
                j = next[j];
            }
        }
        System.out.println(Arrays.toString(next));
        // 2、根据next数组开始匹配
        i = 0;// 指向txt的首元素
        j = 0;// 指向pattern的首元素
        while(i < txt.length() && j < pattern.length()){
            if(j == -1 || txt.charAt(i) == pattern.charAt(j)){
                // 如果j = -1，那么实际情况就是i向后移动一位重新匹配，这时候j也要+1
                i++;
                j++;
            }else{
                // 如果匹配不上，且没有回退到j = -1时，就一直回退，直到匹配或者j=-1时才停止
                j = next[j];
            }
        }
        if(j >= pattern.length()) return i - pattern.length();
        return - 1;
    }

    /**
     * 获取不向后移动的next数组
     */
    public static void getNext(String pattern){
        int[] next = new int[pattern.length()];
        next[0] = 0;
        int j = 0;
        int i = 1;
        while(i < pattern.length()){
            if(j == 0 || pattern.charAt(i) == pattern.charAt(j)){
                if(pattern.charAt(i) == pattern.charAt(j)){
                    j++;
                }
                next[i] = j;
                i++;
            }else{
                j = next[j - 1];
            }
        }
//        for(int i = 1; i < pattern.length(); i++) {
//            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) { // j要保证大于0，因为下面有取j-1作为数组下标的操作
//                j = next[j - 1]; // 注意这里，是要找前一位的对应的回退位置了
//            }
//            if (pattern.charAt(i) == pattern.charAt(j)) {
//                j++;
//            }
//            next[i] = j;
//        }
        System.out.println(Arrays.toString(next));
    }


}
