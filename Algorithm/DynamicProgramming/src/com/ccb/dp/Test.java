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
 *      1、确认可变参数维度：整形【0维】，数组【1维】[可能性太多]     所以一般都是0维
 *      2、确认维度：递归参数中的变量有几个——一个一维，两个二维，三个三维
 *      ------------------------------------------------------
 *      3、确定dp维度表示的含义：递归方法参数的变量取值范围
 *      4、dp数组初始化值：递归终止条件所取的值
 *      5、*确定遍历顺序及计算普通值：递归核心改造
 *      6、dp返回取的数组下标：递归初始传入值
 *      7、优化，如果可以的话
 */
public class Test {
    public static void main(String[] args) {
//        testRobotWalk();
//        testCoinSum();
//        testExtremePointChooseNum();
//        testChess();
        testPeopleWalk();
    }

    private static void testPeopleWalk() {
        int n = 5;
        int m = 5;
        int endX = 2;
        int endY = 2;
        int startX = 0;
        int startY = 0;
        int k = 4;
        System.out.println(PeopleWalk.recursion(n, m, startX, startY, endX, endY, k));
        System.out.println(PeopleWalk.dp(n, m, startX, startY, endX, endY, k));
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

    /**
     * check
     */
    private static void testExtremePointChooseNum() {
        int[] nums = {3, 100, 4, 50};
        System.out.println(ExtremePointChooseNum.recursion(nums));
        System.out.println(ExtremePointChooseNum.dp(nums));
    }

    /**
     * check
     */
    private static void testChess() {
        int targetX = 4;
        int targetY = 5;
        int curX = 0;
        int curY = 0;
        int k = 3;
        System.out.println(Chess.recursion(targetX, targetY, curX, curY, k));
        System.out.println(Chess.dp(targetX, targetY, k));
    }
}
