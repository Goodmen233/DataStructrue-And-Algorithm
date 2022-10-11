package com.ccb.dp;

import java.util.Map;

/**
 * 有1~n的点，机器人从s位置走到e位置必须走k步，可以有几种方法（每次只能往左走或者往右走）
 */
public class RobotWalk {

    private final static String COMMA = ",";

    /**
     * 暴力递归
     *
     * @param n n个点
     * @param e 终点位置
     * @param s 当前位置
     * @param k 还可以走k步
     * @return 方法数
     */
    public static int recursion(int n, int e, int s, int k) {
        if (s < 1 || e > n || k < 0) {
            return 0;
        }
        if (s == e && k == 0) {
            return 1;
        }
        int left = recursion(n, e, s - 1, k - 1);
        int right = recursion(n, e, s + 1, k - 1);
        return left + right;
    }

    /**
     * 记忆化搜索
     * 在递归的基础上加一个缓存，当且仅当递归无后效性的时候可以用
     *
     * @param n n个点
     * @param e 终点位置
     * @param s 当前位置
     * @param k 还可以走k步
     * @param cache 缓存 key为s+k，value为方法数
     * @return 方法数
     */
    public static int recursionCache(int n, int e, int s, int k, Map<String, Integer> cache) {
        if (s < 1 || e > n || k < 0) {
            return 0;
        }
        if (s == e && k == 0) {
            return 1;
        }
        int left = cache.getOrDefault(genKey(s - 1, k - 1), recursion(n, e, s - 1, k - 1));
        int right = cache.getOrDefault(genKey(s + 1, k - 1), recursion(n, e, s + 1, k - 1));
        cache.put(genKey(s, k), left + right);
        return cache.get(genKey(s, k));
    }

    /**
     * 动态规划
     *  1、确定维度：递归中可变参数个数：两个二维
     *  2、确定可变参数维度：0维【整形】
     *
     *  3、确定dp数组的下标含义：横坐标——当前位置，纵坐标——还剩几步，值——方法数
     *  4、确定dp数组的初始值：纵坐标为0的点，除了等于终点的点赋值为1，其余为0
     *  5、确定遍历顺序及计算普通值：递归的核心
     *  6、确定dp结果返回：横坐标——开始位置，纵坐标——还有k步
     *
     * @param n n个点
     * @param e 终点位置
     * @param s 当前位置
     * @param k 还可以走k步
     * @return 方法数
     */
    public static int dp(int n, int e, int s, int k) {
        // +2是为了不判断边界值。。。
        int[][] dp = new int[n + 2][k + 2];
        dp[e][0] = 1;
        for (int curK = 1; curK < k + 1; curK++) {
            for (int cur = 1; cur < n + 1; cur++) {
                dp[cur][curK] = dp[cur - 1][curK - 1] + dp[cur + 1][curK - 1];
            }
        }
        return dp[s][k];
    }

    /**
     * 输出二维数组
     *
     * @param dp 二维数组
     */
    private static void arrOut(int[][] dp) {
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    /**
     * 生成当前位置，剩余步数的key
     * @param s 当前位置
     * @param k 剩余步数
     * @return key
     */
    private static String genKey(int s, int k) {
        return s + COMMA + k;
    }
}
