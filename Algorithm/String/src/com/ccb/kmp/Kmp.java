package com.ccb.kmp;

import java.util.Arrays;

/**
 * 字符串KMP匹配算法
 * 思想：动态规划优化回溯，状态转移图，从之前状态推出现在状态
 * 相当于自己和自己先匹配一遍，然后在和其他匹配
 *
 * 暴力匹配：
 *  如果不匹配，那么txt指针回到第一位，pattern指针也回到第一位
 * Kmp：
 *  如果不匹配，那么txt指针不变，pattern指针回到当前不匹配的位置的next数组位置
 */
public class Kmp {

    /**
     * kmp算法——字符回退位置往后移动一位
     * @param txt 需要找的字符串
     * @param pattern 要匹配的字符串
     * @return 如果存在返回下标，不存在返回-1
     */
    public static int kmp0(String txt, String pattern){
        // 1、根据pattern构造next数组
        int[] next = new int[pattern.length()];// next[i]表示如果i位置不匹配，从next[i]位置重新匹配；表示i-1前和next[i]-1前匹配
        int i = 0;// 指向后缀末尾
        int j = -1;// 指向前缀末尾
        next[0] = j;
        while(i < pattern.length() - 1){
            if(j == -1 || pattern.charAt(i) == pattern.charAt(j)){
                // 如果是i字符和j字符相等，
                //      那么到时候如果pattern[i+1]匹配不上，就跳回pattern[j+1]处重新比较
                // 如果是j = -1的情况，那么就是前缀和后缀毫无匹配关系，只能重头来。
                //      那么到时候如果pattern[i+1]匹配不上，就跳回pattern[0]开始了。
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
    public static void getNext$(String pattern){
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

    /**
     * kmp算法
     * @param source 源字符串
     * @param target 目标字符串
     * @return 目标字符串在源字符串的位置，没有返回-1
     */
    public static int kmp(String source, String target) {
        int[] next = getNext(target);
        int cur1 = 0;
        int cur2 = 0;
        while (cur1 < source.length() && cur2 < target.length()) {
            if (source.charAt(cur1) == target.charAt(cur2)) {
                // 如果相等，一起往后移动
                cur1++;
                cur2++;
            } else if (cur2 == 0) {// next[cur2] = -1
                // 如果不相等，且如果此时cur2已经是第一位了，没得比对了，从cur1的下一位从头开始比对
                cur1++;
            } else {
                // 如果不相等，且如果此时cur2不是第一位了，继续往上一个next比对
                cur2 = next[cur2];
            }
        }
        return cur2 < target.length() ? -1 : cur1 - cur2;
    }

    /**
     * 获取next数组
     * @param target 目标字符串
     * @return next数组
     */
    private static int[] getNext(String target) {
        int[] next = new int[target.length()];
        next[0] = -1;
        next[1] = 0;
        // 不仅指向当前比较的位置【当前字符的前一个字符的next位置】，而且是当前字符的最长前后缀的长度-1
        int curNext = 0;
        // 要找的next数组下标值
        int cur = 2;
        // 从下标为2的位置开始赋值
        while (cur < target.length()) {
            if (target.charAt(cur) == target.charAt(curNext)) {
                // 如果相等，直接赋值，
                next[cur++] = ++curNext;
            } else if (curNext == 0) {
                // 如果不相等，且如果此时curNext已经是第一位，没得比对了，就是0，直接赋值
                next[cur++] = 0;
            } else {
                // 如果不相等，且如果此时curNext不是第一位，继续往上一个next比对
                curNext = next[curNext];
            }
        }
        System.out.println(Arrays.toString(next));
        return next;
    }

}
