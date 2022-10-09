package com.ccb.dp;

import java.util.HashMap;

/**
 * 动态规划：
 *  本质/步骤：
 *      1、递归——无后效性【指定参数，递归返回值是一样的】
 *      2、记忆搜索——空间换时间，防止递归重复计算
 *      3、严格表结构——递归变量有n个，那么，就需要n维数组
 *      4、严格表精致结构——滚动数组/斜率优化
 *  从上往下依次优化，就形成了动态规划
 *  注：某些问题上，记忆搜索和严格表结构 时间复杂度一样
 *
 *  递归优化成动态规划，自底向上考虑：
 *      递归初始传入值——dp返回取的数组下标
 *      递归方法参数的变量取值范围——dp维度表示的含义
 *      递归终止条件——dp数组初始化值
 *      递归核心——dp数组普通值计算赋值
 *
 */
public class Test {
    public static void main(String[] args) {
//        testRobotWalk();
//        testCoinSum();
        testExtremePointChooseNum();
    }

    /**
     * check
     */
    private static void testRobotWalk() {
        int n = 4;
        int e = 1;
        int s = 3;
        int k = 4;
        System.out.println(RobotWalk.recursion(n, e, s, k));
        System.out.println(RobotWalk.recursionCache(n, e, s, k, new HashMap<>(16)));
        System.out.println(RobotWalk.dp(n, e, s, k));
    }

    /**
     * check
     */
    private static void testCoinSum() {
        int[] coins = {1,3,6,1,1,2};
        int sum = 5;
        System.out.println(CoinSum.recursion(coins, sum, 0));
        System.out.println(CoinSum.dp(coins, sum));
    }

    private static void testExtremePointChooseNum() {
        int[] nums = {3, 100, 4, 50};
        System.out.println(ExtremePointChooseNum.recursion(nums));
        System.out.println(ExtremePointChooseNum.dp(nums));
    }
}
