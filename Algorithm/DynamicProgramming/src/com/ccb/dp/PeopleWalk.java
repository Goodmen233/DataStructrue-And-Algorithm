package com.ccb.dp;

/**
 * 有n*m列，指定人从一个开始位置走k步 到达(x, y)位置【越界人就死】。有几种方法？
 */
public class PeopleWalk {

    /**
     * 暴力递归
     * @param n n行
     * @param m m列
     * @param startX 开始x坐标
     * @param startY 开始y坐标
     * @param endX 结束x坐标
     * @param endY 结束y坐标
     * @param k k步
     * @return 方法数
     */
    public static int recursion(int n, int m, int startX, int startY, int endX, int endY, int k) {
        if (startX < 0 || startX > m || startY < 0 || startY > n || k < 0) {
            return 0;
        }
        if (k == 0) {
            if (startX == endX && startY == endY) {
                return 1;
            } else {
                return 0;
            }
        } else {
            // k > 0
            return recursion(n, m, startX + 1, startY, endX, endY, k - 1)
                    + recursion(n, m, startX - 1, startY, endX, endY, k - 1)
                    + recursion(n, m, startX, startY + 1, endX, endY, k - 1)
                    + recursion(n, m, startX, startY - 1, endX, endY, k - 1);
        }
    }

    /**
     * 动态规划
     *
     * @param n
     * @param m
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param k
     * @return
     */
    public static int dp(int n, int m, int startX, int startY, int endX, int endY, int k) {
        int[][][] dp = new int[n + 1][m + 1][k + 1];
        dp[endX][endY][0] = 1;
        for (int z = 1; z <= k; z++) {
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    dp[x][y][z] = getEffectiveValue(dp, x + 1, y, z - 1, n, m)
                            + getEffectiveValue(dp, x - 1, y, z - 1, n, m)
                            + getEffectiveValue(dp, x, y + 1, z - 1, n, m)
                            + getEffectiveValue(dp, x, y - 1, z - 1, n, m);
                }
            }
        }
        return dp[startX][startY][k];
    }

    /**
     * 获取dp数组有效值
     * @param dp dp数组
     * @param x 横坐标
     * @param y 纵坐标
     * @param z 竖坐标
     * @param n 行
     * @param m 列
     * @return 值
     */
    private static int getEffectiveValue(int[][][] dp, int x, int y, int z, int n, int m) {
        return getValue(dp, x, y, z, n, m);
    }

    public static int getValue(int[][][] dp, int x, int y, int z, int n, int m) {
        int xLen = dp.length;
        int yLen = dp[0].length;
        int zLen = dp[0][0].length;
        if (x > m || x < 0 || y > n || y < 0 || z < 0) {
            return 0;
        }
        return dp[x][y][z];
    }
}
