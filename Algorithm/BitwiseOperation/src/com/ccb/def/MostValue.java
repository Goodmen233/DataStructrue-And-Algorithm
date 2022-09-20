package com.ccb.def;

/**
 * 不使用比较符号，通过位运算取得最值
 */
public class MostValue {

    /**
     * 取得最大值
     *  先判断a,b的符号位，diff=a-b
     *      如果a，b同号：不会溢出，直接返回 diffSign * a + ～diffSign * b
     *      如果a，b异号：直接判断谁正谁负 且 diff的符号一定和a相同
     * @param a 数字1
     * @param b 数字2
     * @return 返回a当且仅当
     *          1、a，b同号时，diffSign的值为1
     *          2、a，b异号时，aSign值为1
     */
    public static int ofMax(int a, int b) {
        int diff = a - b;

        int aSign = Basic.getSign(a);
        int bSign = Basic.getSign(b);
        int diffSign = Basic.getSign(diff);
        int isDiffSign = aSign ^ bSign;// 如果a，b符号位不一样，为1
        int isSameSign = Basic.flip(isDiffSign);// 如果a，b符号位一样，为1

        int returnA = isDiffSign * aSign + isSameSign * diffSign;
        int returnB = Basic.flip(returnA);
        return returnA * a + returnB * b;
    }

    /**
     * 取得最小值
     *
     * @param a 数字1
     * @param b 数字2
     * @return
     */
    public static int ofMin(int a, int b) {
        // TODO
        return 0;
    }
}
