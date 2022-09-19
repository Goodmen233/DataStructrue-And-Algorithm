package com.ccb.def;

/**
 * 基础运算
 *      异或运算=无进位加法
 *      与运算=求的每位的进位
 */
public class Basic {

    /**
     * 消除最低位的1
     *
     * @param n 数字
     * @return 消掉之后的值
     */
    public static int removeLowestOneBit(int n) {
        return n & (n - 1);
    }

    /**
     * 获取最低位的1
     * @param n 数字
     * @return 最低位的1所在位置对应的二进制值
     */
    public static int getLowestOneBit(int n) {
        return n & (~n + 1);
    }
}
