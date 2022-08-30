package com.ccb.manacher;

/**
 * 马拉车算法
 */
public class Manacher {

    /**
     * 指定填充的字符
     */
    private final static String FILL_STR = "#";

    /**
     * 求指定字符串的最大回文字串
     * @param target 目标字符串
     * @return 最大回文字串的长度
     */
    public static int getLongestPalindromicSubstring(String target) {
        // 填充字符保证暴力匹配的时候奇偶都能正确寻找
        String fillString = fillString(target, FILL_STR);
        // 记录最长半径
        int maxR = -1;
        // 记录回文字串涉及的最右区域边界
        int right = -1;
        // 记录回文字串涉及的最右区域边界所对应的中心点
        int center = 0;
        // 记录每个字符的回文字串半径
        int[] charR = new int[fillString.length()];

        // 开始遍历求解
        for (int i = 0; i < fillString.length(); i++) {
            // 1、如果当前位置大于right,直接暴力寻找
            // 2、如果当前位置小于等于right
            // 2.1 如果对称点的回文区域在[left, maxR]内，该点回文半径等于对称点
            // 2.2 如果对称点的回文区域在[left, maxR]边界，从该点的对称点的回文半径继续暴力寻找
            // 2.3 如果对称点的回文区域在[left, maxR]外，该点回文半径等于对称点
            // ------------------------------简化-->
            // 先求最小的回文半径，根据回文半径继续往外拓展，更新对应值
            charR[i] = i > right ? 0 : Math.min(charR[2 * center - i], right - i);
            charR[i] = getLongestPalindromicStringR(fillString, i, charR[i]);
            if (charR[i] + i > right) {
                right = charR[i] + i;
                center = i;
            }
            maxR = Math.max(maxR, charR[i]);
        }
        return maxR;
    }

    /**
     * 从指定中心点的指定半径开始寻找回文串
     * @param target 目标字符串
     * @param center 指定中心点
     * @param startR 指定半径
     * @return 回文区域半径
     */
    private static int getLongestPalindromicStringR(String target, int center, int startR) {
        int right = center + startR + 1;
        int left = center - startR - 1;
        int resR = startR;
        while (left >= 0 && right < target.length() && target.charAt(left) == target.charAt(right)) {
            resR++;
            right++;
            left--;
        }
        return resR;
    }

    /**
     * 用指定字符填充目标字符串
     * @param target 目标字符串
     * @param fillStr 指定字符
     * @return 填充后的字符串
     */
    private static String fillString(String target, String fillStr) {
        StringBuilder sb = new StringBuilder(target.length() * 2 + 1);
        for (int i = 0; i < target.length(); i++) {
            sb.append(fillStr);
            sb.append(target.charAt(i));
        }
        sb.append(fillStr);
        return sb.toString();
    }

}
