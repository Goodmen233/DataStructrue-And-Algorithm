package com.ccb.dp;

/**
 * 给定nums[]数组，每次只能从端点两边取数，求取得胜利的最佳分数
 */
public class ExtremePointChooseNum {

    /**
     * 递归
     *
     * @param nums 数值数组
     * @return 最高分
     */
    public static int recursion(int[] nums) {
        return Math.max(first(nums, 0, nums.length - 1), after(nums, 0, nums.length - 1));
    }

    /**
     * 先手获得的分数
     *
     * @param nums 数值数组
     * @param left 左边指标
     * @param right 右边指标
     * @return 最高分数
     */
    private static int first(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        return Math.max(nums[left] + after(nums, left + 1, right),
                nums[right] + after(nums, left, right - 1));
    }

    /**
     * 后手获得的分数
     *
     * @param nums 数值数组
     * @param left 左边指标
     * @param right 右边指标
     * @return 最高分数
     */
    private static int after(int[] nums,int left, int right) {
        if (left == right) {
            return nums[left];
        }
        return Math.min(first(nums, left + 1, right),
                first(nums, left, right - 1));
    }

    /**
     * 动态规划
     *  1、确认可变参数维度：整形【0维】
     *  2、确认维度：递归参数中的变量有几个——两个二维，有两个
     *
     *  3、确定dp维度表示的含义：横坐标——开始取数的左边界，纵坐标——开始取数的右边界，值——最佳分数
     *  4、dp数组初始化值：横坐标等于纵坐标，最高分数就是当前坐标的值
     *  5、*确定遍历顺序及计算普通值：递归核心改造
     *  6、dp返回取的数组下标：横坐标——左边界0，纵坐标——右边界nums.length-1
     *
     * @param nums 数值数组
     * @return 最高分数
     */
    public static int dp(int[] nums) {
        int[][] firstDp = new int[nums.length][nums.length];
        int[][] afterDp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            firstDp[i][i] = nums[i];
            afterDp[i][i] = nums[i];
        }
        int row = 0;
        int col = 1;
        while(col < nums.length) {
            int i = row;
            int j = col;
            while (i < nums.length && j < nums.length) {
                firstDp[i][j] = Math.max(nums[i] + afterDp[i + 1][j], nums[j] + afterDp[i][j - 1]);
                afterDp[i][j] = Math.min(firstDp[i + 1][j], firstDp[i][j - 1]);
                i++;
                j++;
            }
            col++;
        }
        return Math.max(firstDp[0][nums.length - 1], afterDp[0][nums.length - 1]);
    }
}
