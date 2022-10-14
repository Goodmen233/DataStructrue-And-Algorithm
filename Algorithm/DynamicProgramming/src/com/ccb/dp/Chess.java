package com.ccb.dp;

/**
 * 象棋，马【可以从8个方向跳】从(0，0)到另一个指定点，走k步，求方法数
 */
public class Chess {

    /**
     * 棋盘宽
     */
    private final static int X = 8;

    /**
     * 棋盘长
     */
    private static final int Y = 8;

    /**
     * 递归解法
     *
     * @param targetX 目标位置x
     * @param targetY 目标位置y
     * @param curX 当前位置x
     * @param curY 当前位置y
     * @param k 还剩k步
     * @return 还剩k步，从当前位置到目标位置有的方法数
     */
    public static int recursion(int targetX, int targetY, int curX, int curY, int k) {
        if (k < 0 || curX < 0 || curX > X || curY < 0 || curY > Y) {
            return 0;
        }
        if (k == 0 && targetX == curX && targetY == curY) {
            return 1;
        }
        return recursion(targetX, targetY, curX + 1, curY + 2, k - 1)
                + recursion(targetX, targetY, curX + 2, curY + 1, k - 1)
                + recursion(targetX, targetY, curX + 2, curY - 1, k - 1)
                + recursion(targetX, targetY, curX + 1, curY - 2, k - 1)
                + recursion(targetX, targetY, curX - 1, curY - 2, k - 1)
                + recursion(targetX, targetY, curX - 2, curY - 1, k - 1)
                + recursion(targetX, targetY, curX - 2, curY + 1, k - 1)
                + recursion(targetX, targetY, curX - 1, curY + 2, k - 1);
    }

    /**
     * 动态规划：
     *  1、单一变量维度：0维
     *  2、确定dp维度：3个三维
     *  3、确定dp下标意义：[当前位置x][当前位置y][还能走k步] = 到达target的方法数
     *  4、确定dp初始值：[targetX][targetY][0] = 1
     *  5、确定dp核心：8个方向累加
     *  6、确定dp结果：[0][0][k]
     *
     * @param targetX
     * @param targetY
     * @param k
     * @return
     */
    public static int dp(int targetX, int targetY, int k) {
        int[][][] dp = new int[X + 1][Y + 1][k + 1];
        dp[targetX][targetY][0] = 1;
        // 从下往上,一面一面赋值
        for (int z = 1; z <= k; z++) {
            for(int x = 0; x <= X; x++) {
                for (int y = 0; y <= Y; y++) {
                    dp[x][y][z] = getEffectiveValue(dp, x + 1, y + 2, z - 1)
                            + getEffectiveValue(dp, x + 2, y + 1, z - 1)
                            + getEffectiveValue(dp, x + 2, y - 1, z - 1)
                            + getEffectiveValue(dp, x + 1, y - 2, z - 1)
                            + getEffectiveValue(dp, x - 1, y - 2, z - 1)
                            + getEffectiveValue(dp, x - 2, y - 1, z - 1)
                            + getEffectiveValue(dp, x - 2, y + 1, z - 1)
                            + getEffectiveValue(dp, x - 1, y + 2, z - 1);
                }
            }
        }
        return dp[0][0][k];
    }

    /**
     * 获取dp数组有效值
     * @param dp dp数组
     * @param x 横坐标
     * @param y 纵坐标
     * @param z 竖坐标
     * @return 值
     */
    private static int getEffectiveValue(int[][][] dp, int x, int y, int z) {
        int xLen = dp.length;
        int yLen = dp[0].length;
        int zLen = dp[0][0].length;
        if (x > X || x < 0 || y > Y || y < 0 || z < 0) {
            return 0;
        }
        return dp[x][y][z];
    }
}
