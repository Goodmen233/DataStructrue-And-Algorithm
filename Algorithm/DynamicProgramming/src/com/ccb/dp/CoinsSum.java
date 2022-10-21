package com.ccb.dp;

/**
 * 指定货币，每个货币可以使用无数次，凑够指定的货币值的方法数
 */
public class CoinsSum {

    /**
     * 暴力递归
     *
     * @param coins 硬币数组
     * @param cur 取的硬币位置
     * @param target 目标硬币值
     * @return 方法数
     */
    public static int recursion(int[] coins, int cur, int target) {
        int res = 0;
        if (cur == coins.length) {
            return target == 0 ? 1 : 0;
        }
        for (int i = 0; i * coins[cur] <= target; i++) {
            res += recursion(coins, cur + 1, target - i * coins[cur]);
        }
        return res;
    }

    /**
     * 动态规划
     *
     * @param coins 硬币数组
     * @param target 目标硬币值
     * @return 方法数
     */
    public static int dp(int[] coins, int target) {
        int[][] dp = new int[coins.length + 1][target + 1];
        dp[coins.length][0] = 1;
        for (int cur = coins.length - 1; cur >= 0; cur--) {
            for(int value = 0; value < target + 1; value++) {
                for (int i = 0; i * coins[cur] <= value; i++) {
                    dp[cur][value] += dp[cur + 1][value - i * coins[cur]];
                }
            }
        }
        return dp[0][target];
    }

    /**
     * 动态规划-斜率优化
     *
     * @param coins 硬币数组
     * @param target 目标硬币值
     * @return 方法数
     */
    public static int dpOptimizeOfK(int[] coins, int target) {
        int[][] dp = new int[coins.length + 1][target + 1];
        dp[coins.length][0] = 1;
        for (int cur = coins.length - 1; cur >= 0; cur--) {
            for(int value = 0; value < target + 1; value++) {
                dp[cur][value] = dp[cur + 1][value];
                if (value - coins[cur] >= 0) {
                    dp[cur][value] += dp[cur][value - coins[cur]];
                }
            }
        }
        return dp[0][target];
    }

    /**
     * 动态规划-滚动数组，斜率优化
     *
     * @param coins 硬币数组
     * @param target 目标硬币值
     * @return 方法数
     */
    public static int dpOptimizeOfRoll(int[] coins, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int cur = coins.length - 1; cur >= 0; cur--) {
            for(int value = 0; value < target + 1; value++) {
                if (value - coins[cur] >= 0) {
                    dp[value] += dp[value - coins[cur]];
                }
            }
        }
        return dp[target];
    }
}
