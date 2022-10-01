package com.ccb.def;

/**
 * 用位运算实现加减乘除,不考虑结果溢出！
 */
public class Calculate {

    /**
     * 用位运算实现加法
     * 原理：
     *      异或=无进位加法
     *      且=各位的进位
     *      当前结果值和进位做异或和且运算，直到各位的进位为0为止
     * @param a 数字1
     * @param b 数字2
     * @return 加法结果
     */
    public static int add(int a, int b) {
        int tmp;
        while (b != 0) {
            // 无进位相加
            tmp = a ^ b;
            // 下一次计算的进位值
            b = (a & b) << 1;
            a = tmp;
        }
        return a;
    }

    /**
     * 用位元算实现减法
     *
     * @param a 数字1
     * @param b 数字2
     * @return 减法结果
     */
    public static int sub(int a, int b) {
        // b的负数为b取反加一
        int negB = add(~b, 1);
        return add(a, negB);
    }

    /**
     * 用位运算实现乘法
     *
     * @param a 数字1
     * @param b 数字2
     * @return 乘法结果
     */
    public static int mul(int a, int b) {
        int res = 0;
        while (b != 0) {
            // 如果b末尾是1的话，直接在对应位置上加上a
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            b >>>= 1;
            // 使a移动到对应位置
            a <<= 1;
        }
        return res;
    }

    /**
     * 做b次的加a
     *
     * @param a 数字1
     * @param b 数字2
     * @return 乘法结果
     */
    public static int mul0(int a, int b) {
        int res = 0;
        for (int i = 0; i < b; i = add(i, 1)) {
            res = add(res, a);
        }
        return res;
    }

    /**
     * 用位运算实现除法
     *  暴力解：
     *      从32位移动到0位，判断能被减的数，对应位置置1
     * @param a 数字1
     * @param b 数字2
     * @return 除法结果
     */
    public static int div(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("除数不能为0");
        }
        boolean isNegA = a < 0;
        boolean isNegB = b < 0;
        if (isNegA) {
            a = add(~a, 1);
        }
        if (isNegB) {
            b = add(~b, 1);
        }
        // 开始除法
        int res = 0;
        for (int i = 31; i > -1; i = sub(i, 1)) {
            // 用被除数右移 防止溢出风险
            if ((a >> i) >= b) {
                res ^= 1 << i;
                a = sub(a, b << i);
            }
        }
        return isNegA ^ isNegB ? add(~res, 1) : res;
    }

    /**
     * 用a减b看能减几次
     *
     * @param a 数字1
     * @param b 数字2
     * @return 除法结果
     */
    public static int div0(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("除数不能为0");
        }
        boolean isNegA = a < 0;
        boolean isNegB = b < 0;
        if (isNegA) {
            a = add(~a, 1);
        }
        if (isNegB) {
            b = add(~b, 1);
        }
        // 开始除法
        int res = 0;
        while(a >= b) {
            a = sub(a, b);
            res++;
        }
        return isNegA ^ isNegB ? add(~res, 1) : res;
    }
}
