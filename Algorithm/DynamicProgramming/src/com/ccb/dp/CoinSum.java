package com.ccb.dp;

/**
 * 用coins[n]里面的硬币合成指定值sum，使用的最小硬币数[每个硬币只能使用一次，因此coins数组里面可以有重复值]
 */
public class CoinSum {

    /**
     * 递归
     *
     * @param coins 硬币数组
     * @param sum 指定值
     * @param cur 当前位置，之前位置的硬币已经使用过
     * @return 合成sum的最小值
     */
    public static int recursion(int[] coins, int sum, int cur) {
        if (sum == 0) {
            return 0;
        }
        // 规定-1就是凑不出来
        if (sum < 0 || cur >= coins.length) {
            return -1;
        }
        int takeCur = recursion(coins, sum - coins[cur], cur + 1);
        int noTakeCur = recursion(coins, sum, cur + 1);
        if (takeCur == -1 && noTakeCur == -1) {
            return -1;
        } else if (takeCur == -1) {
            return noTakeCur;
        } else if (noTakeCur == -1) {
            return takeCur + 1;
        } else {
            return Math.min(1 + takeCur, noTakeCur);
        }
    }

    /**
     * 动态规划
     *  1、确定维度：递归中可变参数个数——两个二维
     *  2、确定可变参数维度：0维【整形】
     *
     *  3、确定dp数组下标含义：横坐标——开始取硬币的位置，纵坐标——要凑的总和数，值——截止到当前横坐标位置凑到总和数的最少硬币数
     *  4、确定dp数组的初始值：纵坐标为0，都初始化为0；横坐标大于硬币数，初始化为-1
     *  5、确定遍历顺序及计算普通值：对应递归的核心
     *  6、确定dp结果返回：横坐标——开始位置：0，纵坐标——初始要凑的总和数
     *
     * @param coins 硬币值
     * @param sum 目标值
     * @return 最少硬币数
     */
    public static int dp(int[] coins, int sum) {
        int[][] dp = new int[coins.length + 1][sum + 1];

        for (int i = 0; i < coins.length + 1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i < sum + 1; i++) {
            dp[coins.length][i] = -1;
        }

        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 1; j < sum + 1; j++) {
                int takeCur = j - coins[i] >= 0 ? dp[i + 1][j - coins[i]] : -1;
                int noTakeCur = dp[i + 1][j];
                if (takeCur == -1 && noTakeCur == -1) {
                    dp[i][j] = -1;
                } else if (takeCur == -1) {
                    dp[i][j] = noTakeCur;
                } else if (noTakeCur == -1) {
                    dp[i][j] = takeCur + 1;
                } else {
                    dp[i][j] = Math.min(noTakeCur, 1 + takeCur);
                }
            }
        }
        return dp[0][sum];
    }
}
