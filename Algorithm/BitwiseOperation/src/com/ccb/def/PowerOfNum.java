package com.ccb.def;

/**
 * 求某一个数是否是另一个数的幂
 */
public class PowerOfNum {

    /**
     * 判断一个数是否是2的幂
     *      该数二进制上只有一个1
     * @param n 数
     * @return 是否
     */
    public static boolean two(int n) {
        return (n & Basic.removeLowestOneBit(n)) == 0;
    }

    /**
     * 判断一个数是否是4的幂
     *      该数二进制上只有一个1,且在...1010101之间
     * @param n 数
     * @return
     */
    public static boolean four(int n) {
        return (Basic.getLowestOneBit(n) & 0x55555555) != 0 && two(n);
    }
}
