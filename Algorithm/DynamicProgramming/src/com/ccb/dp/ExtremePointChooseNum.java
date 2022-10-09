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
